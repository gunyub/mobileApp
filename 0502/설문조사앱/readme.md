# 📱 SmartUseSurvey

스마트폰 사용 습관을 주제로 한 설문조사 앱입니다.  
**Java + Activity 기반 구조**로 직접 코드를 타이핑하며 구현하는 바이브코딩 방식으로 제작하였습니다.

---

## 🎯 앱 목적

- 스마트폰 사용 습관에 대한 설문 진행
- 사용자 응답을 바 차트와 텍스트로 시각화
- XML을 활용한 UI 설계 + Java로 Activity 제어

---

## 🛠️ 사용 기술

- Java (Android)
- Activity 기반 화면 구성
- MPAndroidChart (그래프 시각화)
- View 요소: `TextView`, `RadioGroup`, `Button`, `BarChart` 등

---

## 🔨 바이브코딩 제작 과정

### ✅ 1단계: 프로젝트 구성

- 프로젝트명: **SmartUseSurvey**
- 언어: Java
- 구조: MainActivity → SurveyActivity → ResultActivity

```plaintext
MainActivity.java         // 설문 시작 버튼
SurveyActivity.java       // 10개 질문과 4지선다 응답
ResultActivity.java       // 응답 요약 + 그래프 출력
