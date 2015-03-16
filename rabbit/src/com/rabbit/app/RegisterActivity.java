package com.rabbit.app;


import com.rabbit.app.util.ClassPathResource;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**ע�����activity*/
public class RegisterActivity extends Activity implements android.view.View.OnClickListener{
	public static final int REGION_SELECT = 1;
	private TextView tv_QQ_Server,tv_region_modify,tv_region_show,tv_top_title;
	private Button btn_title_left,btn_title_right,btn_send_code;
	private CheckBox chk_agree;
	private EditText et_mobileNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		initView();
	}
	
	private void initView(){
		tv_top_title = (TextView) findViewById(R.id.tv_top_title);
		tv_top_title.setText("QQע��");
		
		btn_title_right = (Button) findViewById(R.id.btn_title_right);
		btn_title_right.setVisibility(View.GONE);
		
		btn_title_left = (Button) findViewById(R.id.btn_title_left);
		btn_title_left.setOnClickListener(this);
		
		btn_send_code = (Button) findViewById(R.id.btn_send_code);
		btn_send_code.setOnClickListener(this);
		
		tv_QQ_Server = (TextView) findViewById(R.id.tv_QQ_Server);
		tv_QQ_Server.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		
		tv_region_show = (TextView) findViewById(R.id.tv_region_show);
		
		tv_region_modify = (TextView) findViewById(R.id.tv_region_modify);
		tv_region_modify.setOnClickListener(this);
		
		chk_agree = (CheckBox) findViewById(R.id.chk_agree);
		et_mobileNo = (EditText) findViewById(R.id.et_mobileNo);
	}
	
	/**
	 * ��д��onCreateDialog����������һ���б�Ի���
	 */
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		// TODO Auto-generated method stub
		switch(id){
		case REGION_SELECT:
			final Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("��ѡ�����ڵ�");
			builder.setSingleChoiceItems(//��һ��������Ҫ��ʾ���б�������չʾ���ڶ���������Ĭ��ѡ�е����λ�ã�
					//������������һ���¼����������
					new String[]{"+86�й���½","+853�й�����","+852�й����","+886�й�̨��"},
					0, 
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							switch(which){
							case 0:
								tv_region_show.setText("+86�й���½");
								
								break;
							case 1:
								tv_region_show.setText("+853�й�����");
								break;
							case 2:
								tv_region_show.setText("+852�й����");
								break;
							case 3:
								tv_region_show.setText("+886�й�̨��");
								break;
							}
							dismissDialog(REGION_SELECT);//��Ի���ر�
						}
					});
			return builder.create();
		}
		return null;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv_region_modify:
			showDialog(REGION_SELECT);//��ʾ�б�Ի���ķ���
			break;
		case R.id.btn_title_left:
			RegisterActivity.this.finish();
			break;
		case R.id.btn_send_code:
			String mobiles = et_mobileNo.getText().toString();
			if(chk_agree.isChecked()== false)//��û��ѡcheckbox�޷���������
				Toast.makeText(this, "��ȷ���Ƿ��Ѿ��Ķ�����ѶQQ�������", Toast.LENGTH_LONG).show();
			if(ClassPathResource.isMobileNO(mobiles)==false)//���ֻ������ϸ���֤���μ��������е�������ʽ
				Toast.makeText(this, "��ȷ��д�ֻ��ţ����ǽ���������һ����֤�����", Toast.LENGTH_LONG).show();
			if(ClassPathResource.isMobileNO(mobiles)==true&&chk_agree.isChecked()==true){
				//����ѡ���Һ�����ȷ�����������һ������
				Toast.makeText(this, "�Ѿ������ֻ�������֤�룬��鿴", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(RegisterActivity.this, RegisterConfirmActivity.class);
				startActivity(intent);
			}
		}
		
	}

	

}