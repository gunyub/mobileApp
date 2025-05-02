# 📘 CHAP 08 - 어댑터뷰와 프래그먼트 요약

이 장에서는 안드로이드에서 **리스트 및 페이지 구조를 효율적으로 보여주기 위한 뷰**들을 학습합니다.  
특히 `ListView`, `RecyclerView`, `Spinner`, `Fragment`, `ViewPager`의 사용법과 차이점을 익히는 것이 핵심입니다.

---

## 🧩 어댑터뷰 (AdapterView)

- **데이터와 화면을 연결해주는 중간 역할**
- 주요 뷰 종류:
  - `ListView`, `GridView`, `Gallery`, `Spinner`
- **ArrayAdapter 또는 CustomAdapter**로 데이터 바인딩

### ✅ ListView 예제
- 항목들을 수직으로 보여주는 뷰
- `ArrayAdapter`를 사용하거나, `getView()`를 오버라이드한 커스텀 어댑터 작성

---

## 🔁 RecyclerView

- `ListView`의 성능 개선 버전
- **뷰 재활용(ViewHolder)** 및 **레이아웃 매니저** 사용
- 최근 개발에서 ListView보다 선호됨

### 🔧 필수 구성 요소
1. `RecyclerView.Adapter`  
2. `ViewHolder`  
3. `LayoutManager (Linear / Grid 등)`

### ✅ 예제
- 친구 100명을 3열 그리드로 표시
- 항목 클릭 시 로그로 해당 친구 이름 출력

---

## 📍 Spinner

- 드롭다운 형태의 선택 UI
- 태양계 행성 등 목록 데이터를 선택할 수 있음
- `ArrayAdapter`로 간단히 구성 가능

---

## 🧱 Fragment

- 액티비티 안에서 독립적으로 동작하는 UI 조각
- 여러 개의 프래그먼트를 동적으로 추가/삭제 가능

### ✅ 특징
- 각 프래그먼트는 독립적인 **레이아웃과 생명주기**를 가짐
- 태블릿과 같은 큰 화면에 여러 프래그먼트 배치 가능
- **재사용성**이 뛰어나고 **모듈화된 UI 구성** 가능

### ✅ 예제
- 버튼 클릭 시 Fragment1 ↔ Fragment2 전환

---

## 📄 ViewPager

- 화면을 좌우로 스와이프하여 페이지 전환
- 내부적으로 프래그먼트를 사용
- 이미지 슬라이드, 튜토리얼 화면 등에서 자주 활용

### 🔧 주요 메서드 (PagerAdapter)
- `instantiateItem()` – 페이지 생성
- `destroyItem()` – 페이지 제거
- `getCount()` – 전체 페이지 수
- `isViewFromObject()` – 페이지 식별자 연결




