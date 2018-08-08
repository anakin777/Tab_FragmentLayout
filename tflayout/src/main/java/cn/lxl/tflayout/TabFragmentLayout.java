package cn.lxl.tflayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TabFragmentLayout extends ConstraintLayout {

    private AppCompatActivity mActivity;
    //标签栏高度
    private int tabBarHeight;
    //标签字体大小
    private int tabTextSize;
    //标签字体颜色
    private int tabTextColor;
    //标签选中字体颜色
    private int tabSelectedTextColor;
    //标签图标大小
    private int tabIconSize;
    //标签消息字体大小
    private int msgTextSize;
    //标签消息背景宽度
    private int leftMargin;


    private int[] tabsIds = {R.id.id_tab1,R.id.id_tab2,R.id.id_tab3,R.id.id_tab4,R.id.id_tab5};
    private int[] tvIds = {R.id.tv_tab1,R.id.tv_tab2,R.id.tv_tab3,R.id.tv_tab4,R.id.tv_tab5};
    private int[] ivIds = {R.id.iv_tab1,R.id.iv_tab2,R.id.iv_tab3,R.id.iv_tab4,R.id.iv_tab5};
    private int[] msgIds = {R.id.tv_msgcount1,R.id.tv_msgcount2,R.id.tv_msgcount3,R.id.tv_msgcount4,R.id.tv_msgcount5};

    private OnTabClickListener onTabClickListener;

    public OnTabClickListener getOnTabClickListener() {
        return onTabClickListener;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }
    public interface OnTabClickListener{
        void onClick(int position);
    }


    public TabFragmentLayout(Context context) {
        super(context);
    }

    public TabFragmentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置框架layout
        LayoutInflater.from(context).inflate(R.layout.tab_fragment_layout, this);
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TabFragmentLayout);
        tabBarHeight = array.getDimensionPixelSize(R.styleable.TabFragmentLayout_tabBarHeight, context.getResources().getDimensionPixelSize(R.dimen.dp_65));
        tabTextSize  = array.getDimensionPixelSize(R.styleable.TabFragmentLayout_tabTextSize,  context.getResources().getDimensionPixelSize(R.dimen.dp_11));
        tabTextColor = array.getColor(R.styleable.TabFragmentLayout_tabTextColor, Color.BLACK);
        tabSelectedTextColor = array.getColor(R.styleable.TabFragmentLayout_tabSelectedTextColor, Color.BLACK);
        tabIconSize = array.getDimensionPixelSize(R.styleable.TabFragmentLayout_tabIconSize, context.getResources().getDimensionPixelSize(R.dimen.dp_35));
        leftMargin = (int) (tabIconSize*0.667);
        msgTextSize  = array.getDimensionPixelSize(R.styleable.TabFragmentLayout_msgTextSize,  context.getResources().getDimensionPixelSize(R.dimen.dp_12));
        array.recycle();

    }

    public void setMenus(ArrayList<Tab_Bean> tabs, AppCompatActivity activity){
        mActivity = activity;
        //设置自定义属性
        for (int i=0;i<tabs.size();i++){
            ConstraintLayout tab = this.findViewById(tabsIds[i]);
            final int finalI = i;
            tab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTabClickListener.onClick(finalI);
                }
            });
            tab.setVisibility(View.VISIBLE);
            LayoutParams tabsLp = (LayoutParams) tab.getLayoutParams();
            tabsLp.height = tabBarHeight;
            tab.setLayoutParams(tabsLp);
            TextView title_tab = this.findViewById(tvIds[i]);
            title_tab.setText(tabs.get(i).getTitle());
            title_tab.setTextSize(TypedValue.COMPLEX_UNIT_PX,tabTextSize);
            ImageView icon_tab = this.findViewById(ivIds[i]);
            LayoutParams iconLp = (LayoutParams) icon_tab.getLayoutParams();
            iconLp.width = tabIconSize;
            iconLp.height = tabIconSize;
            icon_tab.setLayoutParams(iconLp);
            if (i==0){
                title_tab.setTextColor(tabSelectedTextColor);
                icon_tab.setImageResource(tabs.get(i).getSelectedIcon());
                mActivity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.fl_content,tabs.get(i).getFragment(),tabs.get(i).getTitle())
                        .commit();
                tabs.get(i).setFragmentAdded(true);
            }else {
                title_tab.setTextColor(tabTextColor);
                icon_tab.setImageResource(tabs.get(i).getGeneralIcon());
            }

            TextView msgCount = this.findViewById(msgIds[i]);
            if (tabs.get(i).getMsgcount()!=0){
                msgCount.setVisibility(View.VISIBLE);
                msgCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,msgTextSize);
                msgCount.setText(""+tabs.get(i).getMsgcount());
                LayoutParams msgLp = (LayoutParams) msgCount.getLayoutParams();
                msgLp.leftMargin = leftMargin;
                msgCount.setLayoutParams(msgLp);
            }else {
                msgCount.setVisibility(View.GONE);
            }
        }
    }

    public void updateMenu(ArrayList<Tab_Bean> tabs,int position){
        for (int i=0;i<tabs.size();i++){
            ImageView icon_tab = this.findViewById(ivIds[i]);
            TextView title_tab = this.findViewById(tvIds[i]);
            if (i==position){
                title_tab.setTextColor(tabSelectedTextColor);
                icon_tab.setImageResource(tabs.get(i).getSelectedIcon());
                if (tabs.get(i).isFragmentAdded()){
                    mActivity.getSupportFragmentManager().beginTransaction()
                            .show(tabs.get(i).getFragment())
                            .commit();
                }else {
                    tabs.get(i).setFragmentAdded(true);
                    mActivity.getSupportFragmentManager().beginTransaction()
                            .add(R.id.fl_content,tabs.get(i).getFragment(),tabs.get(i).getTitle())
                            .commit();
                }

            }else {
                title_tab.setTextColor(tabTextColor);
                icon_tab.setImageResource(tabs.get(i).getGeneralIcon());
                mActivity.getSupportFragmentManager().beginTransaction()
                        .hide(tabs.get(i).getFragment())
                        .commit();
            }
            TextView msgCount = this.findViewById(msgIds[i]);
            if (tabs.get(i).getMsgcount()!=0){
                msgCount.setVisibility(View.VISIBLE);
                msgCount.setText(""+tabs.get(i).getMsgcount());
            }else {
                msgCount.setVisibility(View.GONE);
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
    }


}
