package com.lanchuang.wgc.webviewtest2;

import java.lang.reflect.Method;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class test1 extends Activity
{

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.webview);
		webView = (WebView) findViewById(R.id.wv_webview1);

		// String url = "https://www.baidu.com/";
		String url = "https://github.com/wgcwgc?tab=repositories";
		// �˷���������webview�д����Ӷ�������ת���ⲿ�����
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new myWebChromeClint());
		webView.loadUrl(url);
		setActionBar();
	}

	@Override
	public boolean onKeyDown(int keyCode , KeyEvent event )
	{
		// ��дonKeyDown���������ҳ��WebView���Ժ���ʱִ�к��˲�����
		if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack())
		{
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode ,event);
	}

	class myWebChromeClint extends WebChromeClient
	{
		@SuppressWarnings("static-access")
		@Override
		public void onProgressChanged(WebView view , int newProgress )
		{
			if(newProgress == 100)
			{
				view.setVisibility(view.VISIBLE);
				// progressBar.set
			}
			// super.onProgressChanged(view ,newProgress);
		}
	}

	void setActionBar()
	{
		ActionBar actionbar = getActionBar();
		// ��ʾ���ؼ�ͷĬ���ǲ���ʾ��
		actionbar.setDisplayHomeAsUpEnabled(false);
		// ��ʾ���ķ��ؼ�ͷ�����ҷ��ؼ�ͷ��titleһ�����ã����ؼ�ͷ������ʾ
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayUseLogoEnabled(true);
		// ��ʾ����
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setTitle(" wgcwgc ");
	}
	
	@Override
	public boolean onMenuOpened(int featureId , Menu menu )
	{

		if(featureId == Window.FEATURE_ACTION_BAR && menu != null)
		{
			if(menu.getClass().getSimpleName().equals("MenuBuilder"))
			{
				try
				{
					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible" ,Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu ,true);
				}
				catch(Exception e)
				{
					Toast.makeText(this ,"overflow չ����ʾitemͼ���쳣" ,Toast.LENGTH_LONG).show();
				}
			}
		}

		return super.onMenuOpened(featureId ,menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main ,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item )
	{

		switch(item.getItemId())
		{
			case android.R.id.home:
				// actionbar�����ͼ��ĵ���¼�����
				// finish();
				Toast.makeText(this ,"������һ��" ,Toast.LENGTH_LONG).show();
				onBackPressed();
				break;

			case R.id.action_settings:
				Intent upIntent = NavUtils.getParentActivityIntent(this);
				if(NavUtils.shouldUpRecreateTask(this ,upIntent))
				{
					TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
				}
				else
				{
					upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					NavUtils.navigateUpTo(this ,upIntent);
				}
				Toast.makeText(this ,"������ҳ" ,Toast.LENGTH_LONG).show();
				return true;

//			case R.id.user_p:
//				Toast.makeText(this ,"�����ˡ��û���������" ,Toast.LENGTH_SHORT).show();
//				return true;
//			case R.id.write_p:
//				Toast.makeText(this ,"�����ˡ�������������" ,Toast.LENGTH_SHORT).show();
//				return true;
//			case R.id.favo_p:
//				Toast.makeText(this ,"�����ˡ��ղء�������" ,Toast.LENGTH_SHORT).show();
//				return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
