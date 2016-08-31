package com.lanchuang.wgc.webviewtest2;

import java.lang.reflect.Method;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// setActionBar();

	}

	public void test1(View v )
	{
		Intent intent = new Intent(this , test1.class);
		startActivity(intent);
	}

	public void test2(View v )
	{
		Intent intent = new Intent(this , test2.class);
		startActivity(intent);
	}

	public void test_11(View v )
	{
		Intent intent = new Intent(this , Test_11.class);
		startActivity(intent);
	}

	void setActionBar()
	{
		ActionBar actionbar = getActionBar();
		// 显示返回箭头默认是不显示的
		actionbar.setDisplayHomeAsUpEnabled(true);
		// 显示左侧的返回箭头，并且返回箭头和title一直设置返回箭头才能显示
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayUseLogoEnabled(true);
		// 显示标题
		actionbar.setDisplayShowTitleEnabled(true);
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
					Toast.makeText(this ,"overflow 展开显示item图标异常" ,Toast.LENGTH_LONG).show();
				}
			}
		}

		return super.onMenuOpened(featureId ,menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity ,menu);

		// MenuItem searchItem = menu.findItem(R.id.action_search);
		// SearchView searchView = (SearchView) searchItem.getActionView();
		// 配置SearchView的属性
		// searchView.setBackground(R.drawable.ic_launcher5);
		// return super.onCreateOptionsMenu(menu);
		MenuItem shareItem = menu.findItem(R.id.action_share);
		ShareActionProvider provider = (ShareActionProvider) shareItem.getActionProvider();
		provider.setShareIntent(getDefaultIntent());
		return super.onCreateOptionsMenu(menu);
		// return true;
	}

	private Intent getDefaultIntent()
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		return intent;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item )
	{

		switch(item.getItemId())
		{
			case android.R.id.home:
				// actionbar的左侧图标的点击事件处理
				// finish();
				onBackPressed();
				break;
			case android.R.id.background:
				Toast.makeText(this ,"asdf" ,Toast.LENGTH_LONG).show();
				break;
			case R.id.user_p:
				Toast.makeText(this ,"你点击了“用户”按键！" ,Toast.LENGTH_SHORT).show();
				return true;
			case R.id.write_p:
				Toast.makeText(this ,"你点击了“发布”按键！" ,Toast.LENGTH_SHORT).show();
				return true;
			case R.id.favo_p:
				Toast.makeText(this ,"你点击了“收藏”按键！" ,Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_search:
				Toast.makeText(this ,"你点击了“搜索”按键！" ,Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_settings:
				Toast.makeText(this ,"你点击了“设置”按键！" ,Toast.LENGTH_SHORT).show();
				return true;
			default:
				Toast.makeText(this ,"其他" ,Toast.LENGTH_SHORT).show();

		}

		return super.onOptionsItemSelected(item);
	}
}
