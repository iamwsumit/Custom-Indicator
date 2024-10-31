package com.sumit1334.customindicator;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.util.YailList;
import com.sumit1334.customindicator.animation.type.AnimationType;
import com.sumit1334.customindicator.draw.data.Orientation;

import java.util.ArrayList;
import java.util.List;


public class CustomIndicator extends AndroidNonvisibleComponent {
  private boolean isCreated;
  private PageIndicatorView view;
  private int activeColor;
  private int inactiveColor;
  private Context context;
  private String animation;
  private ArrayList<String> list=new ArrayList<>();
  private int padding;
  private Orientation orientation;
  private int animationDuration;
  private float radius;
  private int strokeWidth;

  public CustomIndicator(ComponentContainer container) {
    super(container.$form());
    this.context= container.$context();
    this.list.add("Color");
    this.list.add("Drop");
    this.list.add("Fill");
    this.list.add("Scale");
    this.list.add("Slide");
    this.list.add("Swap");
    this.list.add("Thin Worm");
    this.list.add("Worm");
    this.isCreated=false;
    this.SelectedColor(Color.RED);
    this.UnselectedColor(Color.YELLOW);
    this.AnimationStyle("None");
    this.Padding(8);
    this.Orientation("Horizontal");
    this.AnimationDuration(250);
    this.Radius(20);
    this.StrokeWidth(2);
  }
  private void setAnimation(String animation){
    if (this.list.contains(animation)) {
      this.animation = animation;
      if (this.isCreated){
        if (this.animation.equals("Swap"))
          this.view.setAnimationType(AnimationType.SWAP);
        else if (this.animation.equals("Drop"))
          this.view.setAnimationType(AnimationType.DROP);
        else if (this.animation.equals("Thin Worm"))
          this.view.setAnimationType(AnimationType.THIN_WORM);
        else if (this.animation.equals("Fill"))
          this.view.setAnimationType(AnimationType.FILL);
        else if (this.animation.equals("Worm"))
          this.view.setAnimationType(AnimationType.WORM);
        else if (this.animation.equals("Slide"))
          this.view.setAnimationType(AnimationType.SLIDE);
        else if (this.animation.equals("Color"))
          this.view.setAnimationType(AnimationType.COLOR);
        else if (this.animation.equals("Scale"))
          this.view.setAnimationType(AnimationType.SCALE);
      }
    }
    else
      this.animation="None";
  }
  @SimpleFunction
  public void Add(AndroidViewComponent in,int total){
    if (!isCreated){
      this.view=new PageIndicatorView(this.context);
      this.view.setLayoutParams(new LinearLayout.LayoutParams(-2,-2,17));
      this.Select(0);
      this.view.setCount(total);
      ((LinearLayout)((ViewGroup) in.getView()).getChildAt(0)).addView(view);
      this.isCreated=true;
      this.Update();
    }
  }
  @SimpleFunction
  public void SetProgress(int index,float progress){
    if (isCreated){
      this.view.setProgress(index-1,progress);
    }
  }
  private void Update(){
    if (isCreated) {
      this.setAnimation(this.animation);
      this.view.setPadding((float) dp2px(this.padding));
      this.view.setRadius(this.radius);
      this.view.setStrokeWidth((float) dp2px(this.strokeWidth));
      this.view.setUnselectedColor(this.inactiveColor);
      this.view.setSelectedColor(this.activeColor);
      this.view.setOrientation(this.orientation);
      this.view.setAnimationDuration(this.animationDuration);
    }
  }
  @SimpleProperty
  public int CurrentIndex(){
    if (isCreated)
      return this.view.getSelection()+1;
    else
      return 0;
  }
  @SimpleFunction
  public YailList GetStylesNames(){
    return YailList.makeList((List) this.list);
  }
  @SimpleFunction
  public void Select(int index){
    if (isCreated)
      this.view.setSelection(index-1);
  }
  @SimpleFunction
  public void Delete(){
    if (isCreated){
      ((ViewGroup) this.view.getParent()).removeView(this.view);
      this.isCreated=false;
    }
  }
  @SimpleProperty
  @DesignerProperty(defaultValue = DEFAULT_VALUE_COLOR_YELLOW,editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
  public void UnselectedColor(int color){
    this.inactiveColor=color;
    this.Update();
  }
  @SimpleProperty
  public int UnselectedColor(){
    return this.inactiveColor;
  }
  @SimpleProperty
  @DesignerProperty(defaultValue = DEFAULT_VALUE_COLOR_RED,editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
  public void SelectedColor(int color){
    this.activeColor=color;
    this.Update();
  }
  @SimpleProperty
  public int SelectedColor(){
    return this.activeColor;
  }
  @SimpleProperty
  @DesignerProperty(defaultValue = "None",editorType = PropertyTypeConstants.PROPERTY_TYPE_CHOICES,editorArgs = {"None","Color","Drop","Fill","Swap","Scale","Slide","Thin Worm","Worm"})
  public void AnimationStyle(String style){
    setAnimation(style);
    this.Update();
  }
  @SimpleProperty
  public String AnimationStyle(){
    return this.animation;
  }
  @SimpleProperty
  @DesignerProperty(defaultValue = "12",editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
  public void Padding(int padding){
    this.padding=padding;
    this.Update();
  }
  @SimpleProperty
  public int Padding(){
    return this.padding;
  }
  @SimpleProperty
  @DesignerProperty(defaultValue = "2",editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
  public void StrokeWidth(int padding){
    this.strokeWidth=padding;
    this.Update();
  }
  @SimpleProperty
  public int StrokeWidth(){
    return this.strokeWidth;
  }
  @SimpleProperty
  @DesignerProperty(defaultValue = "250",editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
  public void AnimationDuration(int padding){
    this.animationDuration=padding;
    this.Update();
  }
  @SimpleProperty
  public int AnimationDuration(){
    return this.animationDuration;
  }
  @SimpleProperty
  @DesignerProperty(defaultValue = "Horizontal",editorType = PropertyTypeConstants.PROPERTY_TYPE_CHOICES,editorArgs = {"Horizontal","Vertical"})
  public void Orientation(String orientation){
    if (orientation.equals("Horizontal"))
      this.orientation=Orientation.HORIZONTAL;
    else if (orientation.equals("Vertical"))
      this.orientation=Orientation.VERTICAL;
    this.Update();
  }
  @SimpleProperty
  public String Orientation(){
    if (this.orientation.equals(Orientation.VERTICAL))
      return "Vertical";
    else if (this.orientation.equals(Orientation.HORIZONTAL))
      return "Horizontal";
    else
      return null;
  }
  @SimpleProperty
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT,defaultValue = "20.0")
  public void Radius(float radius){
    this.radius=radius;
    this.Update();
  }
  @SimpleProperty
  public float Radius(){
    return this.radius;
  }
  private int dp2px(int size){
    return (int) (((float) size) * context.getResources().getDisplayMetrics().density);
  }
}
