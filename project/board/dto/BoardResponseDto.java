package com.example.project.board.dto;


import com.example.project.board.domain.Board;
import com.example.project.reply.dto.ReplyResponseDto;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BoardResponseDto { // 게시글을 클릭했을 때, 댓글들도 나오게 만들기 위해선 DTO에 List<Reply> 추가. / 이승창
    private Long boardId;
    private String title;
    private String contents;
    private String writer;
    private String region;
    private List<ReplyResponseDto> replies;
    private Long theNumberOfReply;
    private LocalDateTime createdDate;
    private Long view;
    private Long hearts;

    // Board 객체를 BoardResponseDto 객체로 변환
    public static BoardResponseDto of(Board board){
        return BoardResponseDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .contents(board.getContents())
                .writer(board.getWriter())
                .replies(board.getReplies().stream()
                        .map(reply -> new ReplyResponseDto(reply.getReplyId(), reply.getWriter(), reply.getContent()))
                        .collect(Collectors.toList())
                )
                .theNumberOfReply(board.getReplies().stream()
                        .map(reply -> new ReplyResponseDto(reply.getReplyId(), reply.getWriter(), reply.getWriter()))
                        .count()
                )
                .createdDate(board.getCreatedDate())
                .view((long)board.getView())
                .hearts((long) board.getHearts().size())
                .build();
    }
}
