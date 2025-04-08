package com.itwill.approval.service;

import com.itwill.approval.dto.*;
import com.itwill.approval.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

	private final FileMapper fileMapper; // 파일 DB 저장용
	private final String uploadDir = "/Users/jiwooahn/uploads"; // 실제 경로로 수정해야 함

	private final ApprovalMapper approvalMapper;

	@Override
	@Transactional
	public void saveApprovalRequest(ApprovalApplyDTO dto, List<MultipartFile> files) {
		// 1. 결재 문서 먼저 저장
		approvalMapper.insertApprovalRequest(dto);

		// 2. 파일 저장
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					String uuid = UUID.randomUUID().toString();
					String originalName = file.getOriginalFilename();
					String saveName = uuid + "_" + originalName;
					String savePath = uploadDir + File.separator + saveName;

					file.transferTo(new File(savePath));

					FileDTO fileDTO = new FileDTO();
					fileDTO.setFileId(uuid);
					fileDTO.setFileName(originalName);
					fileDTO.setFilePath(savePath);
					fileDTO.setFileSize(file.getSize());
					fileDTO.setFileType(file.getContentType());
					fileDTO.setReferenceTableId(dto.getApprovalDocumentId());
					fileDTO.setReferenceTableName("APPROVAL_REQ");
					fileDTO.setUploadDate(LocalDateTime.now());

					fileMapper.insertFile(fileDTO);

				} catch (IOException e) {
					throw new RuntimeException("파일 저장 실패", e);
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

}