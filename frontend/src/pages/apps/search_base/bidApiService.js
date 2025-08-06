import axios from "axios"
import { BID_SEARCH_CONSTANTS, formatDate } from "../../../constants/mapping"

const { API, API_RESPONSE, PAGINATION } = BID_SEARCH_CONSTANTS

export class BidApiService {
  // 데이터 개수 확인
  static async checkDataCount(searchParams) {
    const { startDate, endDate, bidNtceNm, indstrytyCd, regionCode } = searchParams

    const queryParams = {
      inqryDiv: API_RESPONSE.INQUIRY_DIVISION,
      pageNo: API_RESPONSE.PAGE_NUMBER,
      numOfRows: "1",
      inqryBgnDt: formatDate(startDate),
      inqryEndDt: formatDate(endDate, true),
      bidNtceNm,
      ServiceKey: API.API_KEY,
      indstrytyCd,
      prtcptLmtRgnCd: regionCode,
      type: API_RESPONSE.RESPONSE_TYPE,
    }

    const response = await axios.get(API.BASE_URL_BID_LIST, { params: queryParams })
    return response.data.response.body.totalCount
  }

  // 입찰 공고 목록 조회
  static async fetchBidList(searchParams, count) {
    const { startDate, endDate, bidNtceNm, indstrytyCd } = searchParams

    const queryParams = {
      inqryDiv: API_RESPONSE.INQUIRY_DIVISION,
      pageNo: API_RESPONSE.PAGE_NUMBER,
      numOfRows: count,
      inqryBgnDt: formatDate(startDate),
      inqryEndDt: formatDate(endDate, true),
      bidNtceNm,
      ServiceKey: API.API_KEY,
      indstrytyCd,
      prtcptLmtRgnCd: API_RESPONSE.REGION_ALL_CODE,
      type: API_RESPONSE.RESPONSE_TYPE,
    }

    const response = await axios.get(API.BASE_URL_BID_LIST, { params: queryParams })
    return response.data.response.body.items || []
  }

  // 참가 가능 지역 조회
  static async fetchRegionInfo(bidNtceNo, bidNtceOrd) {
    const queryParams = {
      inqryDiv: API_RESPONSE.INQUIRY_DIVISION,
      pageNo: API_RESPONSE.PAGE_NUMBER,
      numOfRows: "1",
      ServiceKey: API.API_KEY,
      bidNtceNo,
      bidNtceOrd,
      type: API_RESPONSE.RESPONSE_TYPE,
    }

    try {
      const response = await axios.get(API.BASE_URL_REGION, { params: queryParams })
      const { resultCode } = response.data.response.header
      const apiItems = response.data.response.body.items

      if (resultCode === API_RESPONSE.SUCCESS_CODE && apiItems !== undefined) {
        return apiItems[0]?.prtcptPsblRgnNm || BID_SEARCH_CONSTANTS.REGION.DEFAULT_NAME
      }
      return BID_SEARCH_CONSTANTS.REGION.DEFAULT_NAME
    } catch (error) {
      console.error("지역 정보 API 요청 실패:", error)
      return BID_SEARCH_CONSTANTS.REGION.API_ERROR_NAME
    }
  }

  // 데이터베이스 관련 API
  static async saveToDatabase(dataToSave) {
    return await axios.post(`${API.BASE_URL_BACKEND_API}/batch`, dataToSave)
  }

  static async fetchFromDatabase(page, size = PAGINATION.ROWS_PER_PAGE) {
    return await axios.get(`${API.BASE_URL_BACKEND_API}?page=${page + 1}&size=${size}&sort=id,asc`)
  }

  static async testApi() {
    return await axios.get(`${API.BASE_URL_BACKEND_API}/test`)
  }

  static async fetchById(id) {
    return await axios.get(`${API.BASE_URL_BACKEND_API}/${id}`)
  }

  static async deleteById(id) {
    return await axios.delete(`${API.BASE_URL_BACKEND_API}/${id}`)
  }

  static async testBackendApi() {
    return await axios.get(`${API.BASE_URL_BACKEND_API}/g2b/test`)
  }

  // admin
  static async deleteAll() {
    return await axios.delete(`${API.BASE_URL_BACKEND_API}/admin/delete-all`)
  }
}
