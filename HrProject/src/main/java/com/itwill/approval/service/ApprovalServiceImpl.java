package com.itwill.approval.service;

import com.itwill.approval.dto.*;
import com.itwill.approval.mapper.ApprovalMapper;
import com.itwill.approval.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

	private final ApprovalMapper approvalMapper;
	private final FileMapper fileMapper;
	private final ServletContext servletContext; // 경로 생성을 위해 필요

	@Override
	@Transactional
	public void saveApprovalRequest(ApprovalApplyDTO dto, List<MultipartFile> files) {
		// 1. 결재 문서 먼저 저장
		approvalMapper.insertApprovalRequest(dto);
		
		// 결재선 insert
		String[] approverIds = dto.getApproverList().split(",");
		for (int i = 0; i < approverIds.length; i++) {
			String approverId = approverIds[i];

			// 직책 조회
			String approverRole = approvalMapper.selectPositionByEmpId(approverId);

			ApprovalLineDTO line = new ApprovalLineDTO();
			line.setApprovalLineId(dto.getApprovalDocumentId() + "_" + (i + 1));
			line.setApprovalDocumentId(dto.getApprovalDocumentId());
			line.setApprovalOrder(i + 1);
			line.setApproverId(approverId);
			line.setApproverRole(approverRole);
			line.setRegDate(LocalDateTime.now());
			line.setRegister(dto.getRequester());

			approvalMapper.insertApprovalLine(line);
		}
		
		// 2. 저장 루트 경로 (웹 기준 상대 경로)
		String uploadFolderPath = servletContext.getRealPath("/resources/upload/approval");

		// 3. 실제 폴더가 없으면 생성
		File folder = new File(uploadFolderPath);
		if (!folder.exists()) folder.mkdirs();

		// 4. 첨부파일 저장
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					String originalName = file.getOriginalFilename();
					String fileId = UUID.randomUUID().toString();
					String saveName = fileId + "_" + originalName;
					String savePath = uploadFolderPath + File.separator + saveName;

					// 저장
					file.transferTo(new File(savePath));

					// DB 저장
					FileDTO fileDTO = new FileDTO();
					fileDTO.setFileId(fileId);
					fileDTO.setFileName(originalName);
					fileDTO.setFilePath("/resources/upload/approval/" + saveName); // 웹 경로
					fileDTO.setFileSize(file.getSize());
					fileDTO.setFileType(file.getContentType());
					fileDTO.setReferenceTableId(dto.getApprovalDocumentId());
					fileDTO.setReferenceTableName("APPROVAL_REQ");
					fileDTO.setUploadDate(LocalDateTime.now());

					fileMapper.insertFile(fileDTO);

				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException("파일 저장 중 오류 발생", e);
				}
			}
		}
	}

	@Override
	public List<ApprovalSearchDTO> searchApprovers(String name) {
		return approvalMapper.searchApprovers(name); // 메서드명 수정
	}

	// 결재선 템플릿 저장 (마스터 + 상세 결재자들)
	@Override
	@Transactional
	public void saveApprovalLineTemplate(ApprovalLineTemplateDTO templateDTO, List<ApprovalLineDetailDTO> detailList) {
		// 1. 템플릿 마스터 저장
		approvalMapper.insertTemplate(templateDTO);

		// 2. 결재자 상세 저장
		for (ApprovalLineDetailDTO detail : detailList) {
			detail.setTemplateId(templateDTO.getTemplateId()); // 외래키 설정
			approvalMapper.insertTemplateDetail(detail);
		}
	}

	// 휴가 INSERT
	@Override
	public void saveLeave(LeaveDTO leaveDTO) {
		approvalMapper.insertLeave(leaveDTO);
	}

	// 출장 INSERT
	@Override
	public void saveBusiness(BusinessDTO businessDTO) {
		approvalMapper.insertBusiness(businessDTO);
	}

	@Override
	public List<ApprovalLineTemplateListDTO> getTemplatesByOwner(String ownerId) {
		return approvalMapper.selectTemplatesByOwner(ownerId);
	}

	@Override
	public List<ApprovalLineDetailDTO> getTemplateDetails(String templateId) {
		return approvalMapper.selectTemplateDetails(templateId);
	}

	@Override
	public boolean isTemplateNameDuplicate(String name) {
		// Mapper를 통해 DB에서 중복된 템플릿명이 있는지 확인
		return approvalMapper.countTemplateName(name) > 0;
	}

	// 소유자 ID로 템플릿 + 결재자 상세 목록 조회 서비스 로직
	@Override
	public List<ApprovalLineTemplateListDTO> getTemplatesWithDetails(String ownerId) {
		return approvalMapper.selectTemplatesWithDetails(ownerId);
	}
	
	@Override
	public void insertApprovalLine(ApprovalLineDTO dto) {
	    // approval_line_id 생성
	    String lineId = dto.getApprovalDocumentId() + "_" + dto.getApprovalOrder();
	    dto.setApprovalLineId(lineId);

	    approvalMapper.insertApprovalLine(dto);
	}

}