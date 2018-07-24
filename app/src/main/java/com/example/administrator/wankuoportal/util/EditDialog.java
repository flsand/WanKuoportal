package com.example.administrator.wankuoportal.util;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;


public class EditDialog extends Dialog {
	private String TAG = "EditDialog";
	private TextView cancel_paypsd,ok_paypsd;
	private EditText psd_paypsd;

	public EditDialog(Context context) {
		super(context);
	}

	public EditDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.txpsd_layout);
		init();
	}

	private void init() {

		psd_paypsd = (EditText) findViewById(R.id.psd_paypsd);
		ok_paypsd = (TextView) findViewById(R.id.ok_paypsd);
		cancel_paypsd = (TextView) findViewById(R.id.cancel_paypsd);
		cancel_paypsd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					EditDialog.this.dismiss();
				} catch (Exception e) {

				}
			}
		});
	}

	/**
	 * 
	 * @param title
	 *            not null
	 * @param
	 */
	public void showDialog( String hint, final onEdit listener) {

		if (listener == null) {
			Log.e(TAG, "listener is null");
			return;
		}
		this.show();

		if (hint != null) {
			psd_paypsd.setHint(hint);
		} else {
			psd_paypsd.setHint("");
		}

		ok_paypsd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onSure(psd_paypsd.getText().toString());
				try {
					EditDialog.this.dismiss();
				} catch (Exception e) {

				}
			}
		});
	}

	public interface onEdit {
		void onSure(String inputContent);
	}

}
