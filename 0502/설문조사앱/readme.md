# 📱 SmartUseSurvey

스마트폰 사용 습관을 주제로 한 설문조사 앱입니다.  
Java 언어와 Activity 기반 구조로, **GPT와 함께 실시간으로 직접 코드를 타이핑하며 구현하는 "바이브코딩(Vibe Coding)" 방식**으로 개발하였습니다.

---

## 🎯 앱 목적

- 스마트폰 사용 습관에 대한 10문항 설문조사 진행  
- 사용자가 응답한 데이터를 텍스트와 바 차트로 시각화  
- Android Studio Java 기반 환경에 대한 실전 연습  

---

## 💬 바이브코딩 제작 과정 상세 기록

본 앱은 GPT와 대화하며, 아래와 같은 질문을 직접 하고 그에 대한 응답을 기반으로 앱을 **한 줄씩 직접 구현**해나갔습니다.

---

### 🧩 Step 1: 앱 기획 및 구조 질문

**Q. 안드로이드 스튜디오에서 설문조사 앱 만들기. 주제는 자유. 항목은 10개. 마지막 화면에 설문결과를 텍스트와 그래프로 표현하고 싶어. 어떤 주제가 좋을까?**  
➡️ GPT는 "스마트폰 사용 습관" 주제를 추천했고, 10개의 질문 항목도 예시로 제공했습니다.

---

### 🧩 Step 2: 언어와 구조 선택

**Q. Java 언어로 만들고 싶은데, Activity 기반이 나을까 Fragment 기반이 나을까?**  
➡️ GPT는 Java + Activity 기반을 추천했고, `MainActivity → SurveyActivity → ResultActivity` 구조를 제안함.

---

### 🧩 Step 3: `MainActivity` 구현

**Q. 설문 시작 화면을 만들어줘. 버튼 누르면 설문으로 넘어가게 하고 싶어.**  
➡️ GPT는 `activity_main.xml`에 TextView와 Button 구성, `MainActivity.java`에서 버튼 클릭 시 `SurveyActivity`로 이동하는 코드 제공.

---

### 🧩 Step 4: `SurveyActivity` 설계

**Q. 질문은 라디오 버튼 4지선다형으로 하고 싶어. 질문 10개 나오게 해줘. 다음 버튼 누르면 다음 질문으로 넘어가고 마지막엔 결과 화면으로 가게 하고 싶어.**  
➡️ GPT는 `String[]` 질문 배열과 `String[][]` 보기 배열, `RadioGroup` 사용 예시, 선택지 체크 후 다음 질문으로 넘어가는 로직, 마지막에 `answers[]`를 넘기는 구조 설계 제공.

---

### 🧩 Step 5: `ResultActivity` 구현

**Q. 마지막 화면에서는 응답한 결과를 텍스트로 요약하고, 그래프로 보여주고 싶어.**  
➡️ GPT는 `TextView`로 요약 내용 출력 + MPAndroidChart로 바 차트 출력 코드까지 제공.

---

### 🧩 Step 6: 그래프 오류 해결

**Q. MPAndroidChart 추가했는데 오류 나. Gradle Sync가 안 돼.**  
➡️ GPT는 `libs.versions.toml` 방식에 맞게 설정하는 방법, 그리고 **`jitpack.io` 저장소가 누락되었음을 진단하고 추가하는 방법을 상세히 안내**해줌.

---

## 🧠 사용한 핵심 GPT 프롬프트 예시 모음

안드로이드 스튜디오에서 설문조사 앱 만들기. 주제는 자유. 결과를 텍스트와 그래프로 표현

Java 언어로 하고 싶고 Activity 기반으로 구성해줘

질문은 라디오 버튼 4지선다형으로 해줘

MPAndroidChart는 어떻게 추가해?

build.gradle.kts에서는 어떻게 구현해?

그래프 에러가 떠. JitPack 저장소 추가해야 해?

바이브코딩 방식으로 직접 구현 중이야. 단계를 나눠서 알려줘


---

## 🛠️ 기술 스택

- **언어**: Java  
- **UI 구성**: XML  
- **앱 구조**: `MainActivity → SurveyActivity → ResultActivity`  
- **그래프 라이브러리**: MPAndroidChart (JitPack에서 직접 설정)  
- **Gradle 방식**: 버전 카탈로그(`libs.versions.toml`) 기반 의존성 관리  

---

## 📌 설치 방법 요약

1. Android Studio에서 프로젝트 열기  
2. `settings.gradle.kts`에 저장소 추가:

```kotlin
maven(url = "https://jitpack.io")

libs.versions.toml에 다음 추가:
mpandroidchart = { group = "com.github.PhilJay", name = "MPAndroidChart", version = "v3.1.0" }

build.gradle.kts에서 다음 추가:
implementation(libs.mpandroidchart)

Gradle Sync → Clean Project → Run

[MainActivity]
 → [SurveyActivity]
   → 질문 1~10 / RadioButton 4지선다 / answers 저장
     → [ResultActivity]
       → Text 요약 + BarChart 시각화
👤 개발자
이름: 김건엽

제작 방식: GPT 협업 + 직접 코딩(Vibe Coding)

앱 목적: Android Java 실전 연습 + 앱 배포용 포트폴리오


---

이제 이 내용을 `.md` 파일로 만들어서 GitHub `README.md`에 붙여넣기만 하면 됩니다!  
필요하다면 `.md` 파일 자체도 드릴게요.
