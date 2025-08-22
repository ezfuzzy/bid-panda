export const appList = [
  {
    to: "/search_base/g2b_bid_search",
    img: "/images/logos/g2b.svg",
    alt: "g2b KONEPS Logo",
    title: "나라장터 입찰 공고 검색",
  },
  {
    to: "/search_base/nuri_g2b_search",
    img: "/images/logos/nuri_g2b.svg",
    alt: "g2b nuri KONEPS Logo",
    title: "누리장터 입찰 공고 검색",
  },
  {
    to: "/C",
    img: "/images/dummy/ezfz.png",
    alt: "C Thumbnail",
    title: "DB에서 검색",
  },
  {
    to: "/D",
    img: "/images/dummy/ezfz.png",
    alt: "D Thumbnail",
    title: "AI 분석 - python",
  },
  {
    to: "/E",
    img: "/images/dummy/ezfz.png",
    alt: "E Thumbnail",
    title: "시각화 대시보드",
  },
  {
    to: "/another-simulator",
    img: "/images/dummy/ezfz.png",
    alt: "Another Simulator Thumbnail",
    title: "Another Simulator",
  },
  {
    to: "/another-simulator",
    img: "/images/dummy/ezfz.png",
    alt: "Another Simulator Thumbnail",
    title: "Another Simulator",
  },
  {
    to: "/another-simulator",
    img: "/images/dummy/ezfz.png",
    alt: "Another Simulator Thumbnail",
    title: "Another Simulator",
  },
  {
    to: "/another-simulator",
    img: "/images/dummy/ezfz.png",
    alt: "Another Simulator Thumbnail",
    title: "Another Simulator",
  },
]

export const myLinkList = [
  { href: "", alt: "", src: "" },
  {
    href: "",
    alt: "",
    src: "",
  },
  {
    href: "",
    alt: "",
    src: "",
  },
]

export const myContactList = [
  { href: "", text: "" },
  {
    href: "",
    text: "",
  },
  { href: "", text: "" },
]

export const regionOptions = [
  { code: "", name: "공고서참조" },
  { code: "00", name: "전국" },
  { code: "11", name: "서울특별시" },
  { code: "26", name: "부산광역시" },
  { code: "27", name: "대구광역시" },
  { code: "28", name: "인천광역시" },
  { code: "29", name: "광주광역시" },
  { code: "30", name: "대전광역시" },
  { code: "31", name: "울산광역시" },
  { code: "36", name: "세종특별자치시" },
  { code: "41", name: "경기도" },
  { code: "42", name: "강원도" },
  { code: "43", name: "충청북도" },
  { code: "44", name: "충청남도" },
  { code: "45", name: "전라북도" },
  { code: "46", name: "전라남도" },
  { code: "47", name: "경상북도" },
  { code: "48", name: "경상남도" },
  { code: "50", name: "제주도" },
  { code: "51", name: "강원특별자치도" },
  { code: "52", name: "전북특별자치도" },
  { code: "99", name: "기타" },
]

export const PRESET_CODES = ["1162", "1164", "1172", "1173", "1192", "1260"]

export const industryOptions = [
  { code: "1162", name: "건물위생관리업" },
  { code: "1164", name: "시설경비업무" },
  { code: "1172", name: "근로자파견사업" },
  { code: "1173", name: "저수조청소업" },
  { code: "1192", name: "소독업" },
  { code: "1260", name: "건물(시설)관리용역" },
]

export const BID_SEARCH_CONSTANTS = {
  // API 관련 상수
  API: {
    BASE_URL_BID_LIST: "https://apis.data.go.kr/1230000/ad/BidPublicInfoService/getBidPblancListInfoServcPPSSrch",
    BASE_URL_REGION: "https://apis.data.go.kr/1230000/ad/BidPublicInfoService/getBidPblancListInfoPrtcptPsblRgn",
    API_KEY: process.env.REACT_APP_BidPublicInfoService_API_KEY_DEC,

    // backend api
    // BASE_URL_BACKEND_API: "http://localhost:8888/api/bidding-notices",
    BASE_URL_BACKEND_API: "/api/bidding-notices",
  },

  // 업종코드 프리셋
  PRESET_CODES: ["1162", "1164", "1172", "1173", "1192", "1260"],

  // 페이징 관련
  PAGINATION: {
    ROWS_PER_PAGE: 20,
    MAX_SEARCH_RESULTS: 999,
    MAX_RESULTS_THRESHOLD: 1000,
  },

  // 날짜 관련
  DATE: {
    DEFAULT_PERIOD_DAYS: 30,
    PREVIOUS_PERIOD_DAYS: 31,
    URGENT_DEADLINE_DAYS: 3,
  },

  // 메시지
  MESSAGES: {
    SEARCH_LOADING: "🔄 검색중...",
    SEARCH_BUTTON: "🔍 검색하기",
    FILTERING: "맞춤 정보 필터링 중...",
    MAX_RESULTS_WARNING: "999개까지 검색합니다.(검색결과의 수가 1000개 이상입니다. 검색 조건을 조정해주세요)",
    COPY_SUCCESS: "텍스트가 클립보드에 복사되었습니다. Crtl + V 로 붙여넣으세요.",
    COPY_FAILED: "클립보드에 복사 실패: ",
    SAVE_ERROR: "저장 중 오류가 발생했습니다.",
    DATE_SELECTION_ERROR: "조회 시작일과 종료일을 선택해주세요.",
  },

  // API 응답 관련
  API_RESPONSE: {
    SUCCESS_CODE: "00",
    INQUIRY_DIVISION: "2",
    PAGE_NUMBER: "1",
    RESPONSE_TYPE: "json",
    REGION_ALL_CODE: "00",
  },

  // 필터링 제외 항목
  EXCLUDE_NOTICE_TYPES: ["취소공고", "연기공고"],

  // 지역 관련
  REGION: {
    DEFAULT_NAME: "공고서참조",
    API_ERROR_NAME: "API 요청 실패",
    NO_INFO_NAME: "지역정보없음",
  },

  // Toast 자동 닫기 시간 (밀리초)
  TOAST_AUTO_CLOSE_TIME: 4000,
}

// 날짜 포맷팅 유틸리티
export const formatDate = (date, end = false) => {
  if (!date) return ""
  const y = date.getFullYear()
  const m = String(date.getMonth() + 1).padStart(2, "0")
  const d = String(date.getDate()).padStart(2, "0")
  return `${y}${m}${d}${end ? "2359" : "0000"}`
}

// 기본 날짜 생성 유틸리티
export const createDefaultDates = () => {
  const today = new Date()
  today.setHours(0, 0, 0, 0)

  const defaultEndDate = new Date()
  defaultEndDate.setHours(23, 59, 0, 0)
  defaultEndDate.setDate(defaultEndDate.getDate() + BID_SEARCH_CONSTANTS.DATE.DEFAULT_PERIOD_DAYS)

  return { today, defaultEndDate }
}
