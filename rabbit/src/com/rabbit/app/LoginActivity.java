package com.rabbit.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**登陆界面activity*/
public class LoginActivity extends Activity implements OnClickListener{
	/**更多菜单，默认收起，点击后展开，再点击收起*/
	private Button btn_login_regist;//注册按钮
	private Button btn_login;//登陆按钮
	/**更所登陆项的菜单是否展开，默认收起*/
	private boolean isShowMenu = false;
	
	private EditText et_qqNum = null;
	private EditText et_qqPwd = null;
	
	public static final int MENU_PWD_BACK = 1;
	public static final int MENU_HELP = 2;
	public static final int MENU_EXIT = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		initView();
	}
	
	private void initView(){
		
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		
		btn_login_regist = (Button) findViewById(R.id.btn_login_regist);
		btn_login_regist.setOnClickListener(this);
		
		et_qqNum = (EditText)findViewById(R.id.et_qqNum);
		et_qqPwd = (EditText)findViewById(R.id.et_qqPwd);
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_login_regist:
			intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_login:
			
			String userNameValue = et_qqNum.getText().toString();
			String passwordValue = et_qqPwd.getText().toString();
		    
			if(/*userNameValue.equals("liu")&&passwordValue.equals("123")*/true)
			{
				intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				this.finish();
			}else{
				Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
			}
			
			break;

		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//创建系统功能菜单
		// TODO Auto-generated method stub
		menu.add(0, MENU_PWD_BACK, 1, "密码找回").setIcon(R.drawable.menu_findkey);
		menu.add(0,MENU_HELP,2,"帮助").setIcon(R.drawable.menu_setting);
		menu.add(0, MENU_EXIT, 3, "退出").setIcon(R.drawable.menu_exit);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case MENU_PWD_BACK:
			break;
		case MENU_HELP:
			break;
		case MENU_EXIT:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
