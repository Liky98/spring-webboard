package com.demo.webboard.util;

import java.util.Map;
import java.util.Optional;

public class Paging {
    /**
     * totalCount   : 게시 글 전체 수
     * pageSize     : 한 페이지의 게시 글 수
     * navSize      : 한단락 크기 (페이징 네비 크기)
     * firstPageNo  : 첫 번째 페이지 번호
     * prevPageNo   : 이전 페이지 번호
     * startPageNo  : 시작 페이지 (페이징 네비 기준)
     * pageNo       : 페이지 번호
     * endPageNo    : 끝 페이지 (페이징 네비 기준)
     * nextPageNo   : 다음 페이지 번호
     * finalPageNo  : 마지막 페이지 번호
     *
     * @param paramsMap
     * @return
     */
    public static Map<String, Object> makePaging(Map<String, Object> paramsMap) {
        // 한 페이지의 게시 글 수
        Integer pageSizeMap = (Integer) paramsMap.get("pageSize");
        int pageSize = Optional.ofNullable(pageSizeMap).filter(n -> n != 0).orElse(10);
        // 게시 글 전체 수
        Integer totalCountMap = (Integer) paramsMap.get("totalCount");
        int totalCount = Optional.ofNullable(totalCountMap).orElse(0);
        if (totalCount == 0) {
            paramsMap.put("pageSize", pageSize);
            paramsMap.put("totalCount", 0);
            return null;    // 게시글 전체 수가 없는 경우
        }

        // 한단락 크기 (페이징 네비 크기)
        Integer navSizeMap = (Integer) paramsMap.get("navSize");
        int navSize = Optional.ofNullable(navSizeMap).filter(n -> n != 0).orElse(10);
        // 페이지 번호
        Integer pageNoMap = (Integer) paramsMap.get("pageNo");
        int pageNo = Optional.ofNullable(pageNoMap).filter(n -> n != 0).orElse(1);

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
        paramsMap.put("totalCount", totalCount);
        paramsMap.put("pageSize", pageSize);
        paramsMap.put("navSize", navSize);
        paramsMap.put("firstPageNo", firstPageNo);
        paramsMap.put("prevPageNo", prevPageNo);
        paramsMap.put("startPageNo", startPageNo);
        paramsMap.put("pageNo", pageNo);
        paramsMap.put("endPageNo", endPageNo);
        paramsMap.put("nextPageNo", nextPageNo);
        paramsMap.put("finalPageNo", finalPageNo);

        return paramsMap;
    }
}
