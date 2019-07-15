#  Service 란
안드로이드 4대 컴포넌트 중 하나인 서비스는 백그라운드에서 작업 을 처리함.

* 특징
	1. 사용자 인터페이스(UI) 를 제공하지 않음
	2. 다른 앱이 실행 되더라도 백그라운드 상에서 계속 돌아감
	3. Activity 상에서 서비스를 바인드 를 하여 상호작용 할 수 있음.
	4. 하나의 프로세스에서 상주도 가능 하며 별도 프로세스 로 분리 도 가능함
	
# IntentService
IntentService 는 Service 의 서브 클래스 이며 Worker Thread 로 동작 하며
한번에 하나씩 작업을 수행한다.
또한 개발자가 lifecycle 를 관리 할 필요가 없다. 작업이 완료되면 자동으로 종료 된다. 


IntentService 구현부에 보면 Looper 와 WorkerThread 사용을 볼 수 있다.

여기서 Looper 란  작업을 Queue 대기열에 쌓아놓고 하나씩 꺼내서 작업을 처리하는 역할 을 한다.
```java
private volatile Looper mServiceLooper;
```
```java
@WorkerThread  
protected abstract void onHandleIntent(@Nullable Intent intent);
```

IntentService 의 장점은 쉽게 Service 를 구현 할 수 있다.
아래 코드처럼 클래스에 IntentService를 상속 받고 생성자 와 onHandleIntent 만 구현 해주면된다 여기서 onHandleIntent 부분에서 작업을 수행한다.

**주의 : 생성자를 필이 구현해야하며 해당 파라미터에는 String 값으로 서비스명을 넘겨주면 된다. 해당 값은 디버깅 용으로만 사용된다.**

```java
public class IntentServiceExample extends IntenetService
{
	public  IntentServiceExample()  {
	  super("IntentServiceExample");
	}

	@Override  
	protected void onHandleIntent(Intent intent) {  
	    ...
	    ..
	    .
	}
}
```

IntentService 를 이용 할 경우 반환값을 제공하지 않는다.

반환값이 필요할 경우 Messagener , BroadCastReciver , PendingIntent 를 이용하여 IntentService 를 호출하면 onActivityResult 메소드를 사용할 수 있다. 

# Service
IntentService 에서는 다중처리를 할 수 가 없다. 혹여 다중 처리를 하고 싶다면
Service 를 상속 받아 구현 할 수 있다.

```java
@Override  
public int onStartCommand(Intent intent, int flags, int startId) {  
    return super.onStartCommand(intent, flags, startId);  
}
```
START_STICKY : 강제 종료 되었을때 StartCommand() 메소드가 호출 되는데 이때 Intent 값이 Null 로 설정 되어 재시작 된다. 

START_NOT_STICKY : 강제 종료 되었을때 서비스를 재시작 하지 않는다.

START_REDELIVER_INTENT : 강제 종료 되었을때 Intent 값을 유지시켜준다. 

# BoundedService
애플리케이션 구성 요소가 자신에게 바인드될 수 있도록 허용하는 서비스이다.

Activity 와 어플레이케이션 에 구성요소와 상호작용 할 수 있도록 해준다.

bindService() 메소드를 사용하여 시작한다.

# Service LifeCycle

![](https://developer.android.com/images/service_lifecycle.png)



# Android O 백그라운드 서비스 제한
백그라운드 서비스는 사용자가 이용하지 않는 데도 계속  리소스를 사용 하고 있기때문에 사용자가 인지 하기 어려움.

Android O version 부터는 위와 같은 문제들 때문에 백그라운드 서비스의 제한이 생김

그럼 어찌 해야하는가 ?
	- startForegroundService() 를 이용하여 사용 할수 있다 **(해당 함수 실행후 5초 안에 Notification 과 연결해야 함.)**
	- JobSchedular 이용


# Reference
[https://medium.com/til-kotlin-ko/android-o%EC%97%90%EC%84%9C%EC%9D%98-%EB%B0%B1%EA%B7%B8%EB%9D%BC%EC%9A%B4%EB%93%9C-%EC%B2%98%EB%A6%AC%EB%A5%BC-%EC%9C%84%ED%95%9C-jobintentservice-250af2f7783c](https://medium.com/til-kotlin-ko/android-o%EC%97%90%EC%84%9C%EC%9D%98-%EB%B0%B1%EA%B7%B8%EB%9D%BC%EC%9A%B4%EB%93%9C-%EC%B2%98%EB%A6%AC%EB%A5%BC-%EC%9C%84%ED%95%9C-jobintentservice-250af2f7783c)


[https://developer.android.com/guide/components/bound-services.html?hl=ko](https://developer.android.com/guide/components/bound-services.html?hl=ko)

[https://developer.android.com/guide/components/services?hl=ko](https://developer.android.com/guide/components/services?hl=ko)
