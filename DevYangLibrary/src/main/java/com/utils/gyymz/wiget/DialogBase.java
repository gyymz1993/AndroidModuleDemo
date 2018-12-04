package com.utils.gyymz.wiget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/** @author Aidan Follestad (afollestad) */
class DialogBase extends Dialog implements DialogInterface.OnShowListener {

  protected View view;
  private OnShowListener showListener;

  DialogBase(Context context, int theme) {
    super(context, theme);
  }

  @Override
  public View findViewById(int id) {
    return view.findViewById(id);
  }

  @Override
  public final void setOnShowListener(@Nullable OnShowListener listener) {
    showListener = listener;
  }

  final void setOnShowListenerInternal() {
    super.setOnShowListener(this);
  }

  final void setViewInternal(View view) {
    super.setContentView(view);
  }

  @Override
  public void onShow(DialogInterface dialog) {
    if (showListener != null) {
      showListener.onShow(dialog);
    }
  }

  @Override
  @Deprecated
  public void setContentView(int layoutResID) throws IllegalAccessError {
    throw new IllegalAccessError(
        "setContentView() is not supported in OneToucDialog. Specify a custom view in the Builder instead.");
  }

  @Override
  @Deprecated
  public void setContentView(View view) throws IllegalAccessError {
    throw new IllegalAccessError(
        "setContentView() is not supported in OneToucDialog. Specify a custom view in the Builder instead.");
  }

  @Override
  @Deprecated
  public void setContentView(View view, @Nullable ViewGroup.LayoutParams params)
      throws IllegalAccessError {
    throw new IllegalAccessError(
        "setContentView() is not supported in OneToucDialog. Specify a custom view in the Builder instead.");
  }
}
