package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookCreateRequestDTO;
import com.app.springapp.domain.dto.response.GuestbookResponseDTO;
import com.app.springapp.domain.vo.GuestbookVO;
import com.app.springapp.exception.GuestbookException;
import com.app.springapp.repository.GuestbookDAO;
import com.app.springapp.repository.GuestbookReplyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookDAO guestbookDAO;
    private final GuestbookReplyDAO guestbookReplyDAO;

    @Override
    public void createGuestbook(GuestbookCreateRequestDTO guestbookCreateRequestDTO) {
        guestbookDAO.save(guestbookCreateRequestDTO);
    }

    @Override
    public List<GuestbookResponseDTO> findAllByOwnerMemberId(GuestbookResponseDTO guestbookResponseDTO) {
        List<GuestbookResponseDTO> list = guestbookDAO.findAllByOwnerMemberId(guestbookResponseDTO)
                .orElseThrow(() -> new GuestbookException("해당하는 유저가 없습니다."));
        list.forEach(dto -> dto.setReplies(
                guestbookReplyDAO.findAllByGuestbookId(dto.getId()).orElseGet(Collections::emptyList)
        ));
        return list;
    }

    @Override
    public GuestbookResponseDTO findById(Long id) {
        return guestbookDAO.findById(id).orElseThrow(()-> new GuestbookException("해당하는 게시글이 없습니다."));

    }

    @Override
    public List<GuestbookResponseDTO> findAllByText(String keyword) {
        return guestbookDAO.findAllByText(keyword).orElseGet(Collections::emptyList);
    }

    @Override
    public void updateGuestbook(GuestbookVO guestbookVO) {
        guestbookDAO.updateGuestbookContentByWriterMemberId(guestbookVO);
    }

    @Override
    public void deleteGuestbook(GuestbookVO guestbookVO) {
        guestbookDAO.deleteGuestbookByWriterMemberIdAndOwnerMemberId(guestbookVO);
    }
}