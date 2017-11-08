# NeonTextView
霓虹灯、彩虹效果 TextView 
#####  compile 'com.gusi.neontv:NeonTv:1.0.0'
#####  
         <com.gusi.neontv.NeonTextView
            android:id="@+id/tv3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:padding="10dp"
            android:background="#fff"
            android:gravity="center_horizontal"
            android:text="Hello World!\n 你好!你好!"
            app:exchange_time="500"  //霓虹灯效果下变化时间,默认1000(单位毫秒)
            app:layout_constraintLeft_toRightOf="@+id/tv1"
            app:layout_constraintTop_toTopOf="parent"
            app:neon_orientation="horizontal"  //霓虹灯效果下变化方向
            app:open_neon="false" //霓虹灯效果 
            />
 
![image](https://github.com/batuer/NeonTextView/blob/master/img/2017-11-08-01mzimg.gif)
        
