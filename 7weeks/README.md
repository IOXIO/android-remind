## ViewGroup

- Android의 모든 위젯은 View를 상속하여 구현하고 있다.

  ![View hierarchy](https://o7planning.org/en/10423/cache/images/i/1189616.png)

### 주요 ViewGroup 종류와 사용법

#### LinearLayout

- 명칭에서 알 수 있듯이 선형 모양의 레이아웃
- `orientation` 이라는 필수 속성이 필요하며 지정하지 않을 경우 `horizontal` 이 기본

```xml
<LinearLayout
    android:layout_width="match_parent|wrap_content"
    android:layout_height="match_parent|wrap_content"
    android:orientation="vertical|horizontal">
    <!-- other code -->
</LinearLayout>
```

#### RelativeLayout

- 부모 또는 특정 View를 기준으로 특정 View의 상대 위치를 지정할 수 있는 레이아웃

```xml
<RelativeLayout
    android:layout_width="match_parent|wrap_content"
    android:layout_height="match_parent|wrap_content">
    
    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@id/textView1" />
    <!-- other code -->
</RelativeLayout>
```

#### FrameLayout

- 하나의 View 위젯을 표현하기 위한 레이아웃
- 단, 레이아웃 하위에 여러 View 위젯을 추가할 순 있다.

```xml
<FrameLayout
    android:layout_width="match_parent|wrap_content"
    android:layout_height="match_parent|wrap_content">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/abc" />
    <!-- other code -->
</FrameLayout>
```

#### ConstraintLayout

- 뷰와 뷰 사이에 제약조건을 설정하여 위젯을 배치하기 위한 레이아웃
- 속성이 엄청나게 많습니다. ~~(알아서 찾아보세요...)~~

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent|wrap_content"
    android:layout_height="match_parent|wrap_content">
  
    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
    <!-- other code -->
</androidx.constraintlayout.widget.ConstraintLayout>
```

### 주요 Widget 종류와 사용법

#### RecyclerView

- 기존의 ListView의 단점과 성능을 개선하여 제공되는 위젯
- ViewHolder 패턴을 강제하여 아이템 View의 재사용성을 적극 활용한다.

```xml
<RecyclerView
    android:id="@+id/exampleList"
    android:layout_width="match_parent|wrap_content"
    android:layout_height="match_parent|wrap_content" />
```

```kotlin
fun setupRecyclerView() {
    ExampleAdapter adapter = new ExampleAdapter()
    LinearLayoutManager layoutManager = new LayoutManager(this)
    with(exampleList) {
        this.layoutManager = layoutManager
        this.adapter = adpater
    }
}
```

```kotlin
class ExampleAdapter : RecyclerView.Adapter<ExampleHolder>() {
    private var dataSet = mutableListOf<String>()
  
    fun addItems(items: List<String>) {
        dataSet.addAll(items)
        notifyDataSetChanged()
    }
    
    fun updateItems(items: List<String>) {
        dataSet = items as MutableList<String>
        notifyDataSetChanged()
    }
  
    class ExampleHolder(val containerView: View) : RecyclerView.ViewHolder(view) {
        // other code
    }
    
  
  abstract class LayoutContainerViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView)
  
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleHolder {
        return ExampleHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_example, parent, false)
        )
    }
  
    override fun onBindViewHolder(holder: ExampleHolder, position: Int) {
        // otehr code...
    }
  
    override fun getItemCount(): Int = dataSet.size
}
```

#### ViewPager

- 스와이프 액션을 통해 화면을 이동하기 위한 위젯
- ~~샘플 코드 귀찮아요...~~

```xml
<android.support.v4.view.ViewPager
    android:id="@+id/examplePager"
    android:layout_width="match_parent|wrap_content"
    android:layout_height="match_parent|wrap_content" />
```

