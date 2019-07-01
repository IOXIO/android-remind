# Fragment 톺아보기

---

## 1. Overview
## 2. Lifecycle
## 3. Create
## 4. Communication

--- 

## <u>**Overview**</u>

- 하나의 Activity에서 여러개의 Fragment 조합
- Activity LifeCycle에 직접적인 연관
- 자체적인 LifeCycle
- 추가 및 제거 가능
- 재사용

---
## <u>**Overview**</u>

- Adnroid 3.0 도입
- 태블릿과 같은 큰 화면 대응
- android.app.~~Fragment~~
- android.support.v4.app.Fragment 사용
- ~~개인적으론 구글의 실수 같음~~

---

## <u>**Lifecycle**</u>

- ### onAttach(context: Context)
1. Context가 주어지며 부모 Activity에서  
구현한 Listener를 가져올 수 있다.

---

## <u>**Lifecycle**</u>

- ### onCreate(savedInstanceState: Bundle?)
1. 초기화해야하는 리소스들 (Activity와 마찬가지)
2. Fragment 생성시 넘겨 준 값들 할당
3. 단, UI는 초기화 할 수 없다.

---

## <u>**Lifecycle**</u>

- ### onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

1. 레이아웃 inflate
2. Return
3. UI 초기화
---

## <u>**Lifecycle**</u>

- ### onActivityCreated(savedInstanceState: Bundle?)
1. Fragment의 뷰 계층 구조가 인스턴스화 될 때 호출
2. Activity와 드디어 연결된 시점
3. 사용자 인터페이스 작성

---

## <u>**Lifecycle**</u>

- ### onViewStateRestored(savedInstanceState: Bundle?)
1. onSaveInstanceState 시 저장한 값

---

## <u>**Lifecycle**</u>

- ### onStart()

---

## <u>**Lifecycle**</u>

- ### onResume()
1. Activity와 비슷
2. 유저와 상호작용 가능

---

## <u>**Lifecycle**</u>

- ### onPause()
1. 저장해야 될 View 데이터 처리

---

## <u>**Lifecycle**</u>

- ### onSaveInstanceState(outState: Bundle)
1. 필요시 상태저장
2. Activity Destory 시 호출
	1. Activity 전환 O
	2. Fragment Replace/Add X
	3. 바로 View값 접근하면 타이밍 이슈 (pause에서 저장)

---

## <u>**Lifecycle**</u>

- ### onStop()
- ### onDestroyView()
- ### onDestroy()
- ### onDetach()

---

## <u>**Create**</u>
1. SupportFragmentManager
2. beginTransaction/commit
3. addToBackStack
4. replace
5. add/remove
6. show/hide

---

## <u>**Create**</u>

1. 생성자 매개변수 전달(안스2.3가능) 또는 setter <br> -> 화면회전 후 파라미터 ?
2. Fragment는 Configuration change가 발생 또는 <br> 시스템에 의해 재생성 되면 기본 생성자로 인스턴스가 재생성

그럼 초기값은 어떻게..?

---

## <u>**Create**</u>
- ### setArguments(@Nullable Bundle args)
```java
if (this.mIndex >= 0 && this.isStateSaved()) {
  throw new IllegalStateException
  ("Fragment already active and state has been saved");
} else {
  this.mArguments = args;
}
```
- ### Bundle getArguments()
```java
return this.mArguments;
```

---

## <u>**Create**</u>
- ### newInstance() 이유

---

## <u>**Create**</u>
- ### context?
Caused by: kotlin.KotlinNullPointerException

```java
@Nullable
public Context getContext() {
    return this.mHost == null ? null : this.mHost.getContext();
}
```

---

## <u>**Communication**</u>
1. Fragment -> Activity
2. Activity -> Fragment
---

## <u>**끝**</u>
### 감사합니다.

---