## 가계부 시스템

### 서비스 설명

- 가계부의 내역을 등록하여 월/일/항목 별로 출력하게 함.<br>
- 회원기능을 도입하여 타 회원과 가계부를 공유할 수 있음.<br>
- 시스템 시작 시 관리자를 통해 관리되는 명언이 랜덤으로 출력됨.<br>
- 회원/가계부/내역의 CRUD<br>
<br/>


## 🛠 개발환경
| 분류 | 설명 |
|:--------:|:--------:|
| 운영체제  | windows10   |
| 통항 개발 환경   | InteliJ   |
| 프로그래밍 언어   | Java   |
| 버전 관리 시스템   | Git, Github   |
| 데이터베이스   | MySQL   |

<br/>
<br/>

## ☁️ ERD

![사진명](https://i.imgur.com/uOoPShg.png)

<br>
<br>

## 👀 시연영상
[![이미지 텍스트](스크린샷 이미지)](유투브링크)

[![Video Label](http://img.youtube.com/vi/'유튜브주소의id'/0.jpg)](https://youtu.be/'유튜브주소의id')

## 🔥 트러블 슈팅

### 🚨 Issue 1
### 🚧 리팩토링 이슈

A. 이슈 내역
- 컨트롤러에서 다른 컨트롤러의 메서드를 호출함<br>

문제점 설명
- AccountBook 컨트롤러에서 명령어 호출하는 메서드 History 컨트롤러를 사용<br>
## 🛑 원인
- 명령어를 입력받아 타 클래스에 접근하는 기능을 구현할 때, main -> App -> Member -> AccountBook -> History의 상속적인 형태로 만들게 되었음.
이 과정에서 해당 클래스 하나의 기능 구현을 끝내고 다른 클래스의 기능을 구현을 하다보니 더 이상 App에서는 History에 접근하기 힘든 형태가 되었음.

## 🚥 해결
- 데이터를 다루지 않고 명령어를 입력받는 해당 메서드 하나뿐이라 기능 구현에는 큰 문제가 없었지만, 후에 유지보수하는 과정에서 매우 힘들거라 예상함.
애초에 기획/설계 단계에서 어떠한 형태로 만들것인지, ERD에서의 관계를 정확히 해야함.
<br>

### 🚨 Issue 2
### 🚧 출력 이슈

A. 이슈 내역
- 월/일/항목 별 내역 출력 시 가시성이 매우 떨어짐.<br>

문제점 설명
- 내역을 출력하면 출력화면에서 표의 형태로 출력되지않아 가계부로서의 기능을 하기 힘듬<br>
## 🛑 원인
- 내역을 문자/숫자의 형태로 입력받을 때, 글자 수가 일정하지 않음.
- 수입/지출 항목이 통화의 형태가 아니라 단순 숫자로 나타남.

## 🚥 해결
- 숫자는 String.format을 활용하여 출력되는 글자 수를 제한하고, (#,###￦)의 형태로 출력되게함.
- 문자도 String.format을 활용하여 글자 수를 제한하고, 각 글자 수에 맞게 " "공백을 추가되게함.
<br>

### 🚨 Issue 3
### 🚧 합계 이슈

A. 이슈 내역
- 내역 출력 시 합계를 출력하는 과정에서 오류 발생.<br>

문제점 설명
- SQL문을 활용하여 수입/지출 항목의 합계 데이터를 받아오는 과정에서 오류가 발생<br>
## 🛑 원인
- 데이터베이스에서 합계 결과가 정수 타입을 초과하거나 부동 소수점으로 인해 정확한 값을 표현하기 어려운 경우, BigDecimal로 자동 형 변환이 이루어짐.

## 🚥 해결
- 글로벌에 bigdecimal.intValue를 반환하는 메서드를 만들어 DBConnection에서 int로 반환하는 selectRowIntValue메서드에 추가하여 int형으로 형변환.
<br>
