package com.demo.webboard.util;

import lombok.Data;

import java.util.Optional;

@Data
public class Paging {
    private int totalCount;    // 게시 글 전체 수
    private int pageSize;    // 한 페이지의 게시 글 수
    private int navSize;    // 한단락 크기 (페이징 네비 크기)
    private int firstPageNo;    // 첫 번째 페이지 번호
    private int prevPageNo;    // 이전 페이지 번호
    private int startPageNo;    // 시작 페이지 (페이징 네비 기준)
    private Integer pageNo;    // 페이지 번호
    private int endPageNo;    // 끝 페이지 (페이징 네비 기준)
    private int nextPageNo;    // 다음 페이지 번호
    private int finalPageNo;    // 마지막 페이지 번호
    private String url;



    public void makePaging() {
        // 한 페이지의 게시 글 수
        int pageSize = Optional.ofNullable(this.pageSize).filter(n -> n != 0).orElse(10);
        // 게시 글 전체 수
        int totalCount = Optional.ofNullable(this.totalCount).orElse(0);
        if (totalCount == 0) {
            this.setPageSize(pageSize);
            this.setTotalCount(0);
            return;    // 게시글 전체 수가 없는 경우
        }

        // 한단락 크기 (페이징 네비 크기)
        int navSize = Optional.ofNullable(this.navSize).filter(n -> n != 0).orElse(10);
        // 페이지 번호
        int pageNo = Optional.ofNullable(this.pageNo).filter(n -> n != 0).orElse(1);

        // 시작 페이지 번호
        int firstPageNo = 1;
        // 마지막 페이지 번호
        int finalPageNo = (totalCount + (pageSize - 1)) / pageSize;

        // 현재페이지 번호 설정
        if (pageNo <= 0 || pageNo > finalPageNo) {
            pageNo = 1;
        }

        // 시작 페이지 (페이징 네비 기준)
        int startPageNo = ((pageNo - 1) / navSize) * navSize + 1;
        // 끝 페이지 (페이징 네비 기준)
        int endPageNo = startPageNo + navSize - 1;
        if (endPageNo > finalPageNo) {
            endPageNo = finalPageNo;
        }

        boolean isNowFirst = (startPageNo - navSize < firstPageNo ? true : false);   // 페이징 네비 기준 시작페이지가 전체 시작페이지(1) 보다 작은가?
        boolean isNowFinal = (endPageNo >= finalPageNo) ? true : false;     // 페이징 네비 기준 끝페이지가 전체 마지막페이지보다 큰가?

        // 이전 페이지 번호
        int prevPageNo;
        if (isNowFirst) {
            prevPageNo = 1;
        } else {
            prevPageNo = startPageNo - navSize;    // 한단락 이전 페이지 번호
        }

        // 다음 페이지 번호
        int nextPageNo;
        if (isNowFinal) {
            nextPageNo = finalPageNo;
        } else {
            nextPageNo = startPageNo + navSize;     // 한단락 이후 페이지 번호
        }

        //
        this.setTotalCount(totalCount);
        this.setPageSize(pageSize);
        this.setNavSize(navSize);
        this.setFirstPageNo(firstPageNo);
        this.setPrevPageNo(prevPageNo);
        this.setStartPageNo(startPageNo);
        this.setPageNo(pageNo);
        this.setEndPageNo(endPageNo);
        this.setNextPageNo(nextPageNo);
        this.setFinalPageNo(finalPageNo);

    }
}
