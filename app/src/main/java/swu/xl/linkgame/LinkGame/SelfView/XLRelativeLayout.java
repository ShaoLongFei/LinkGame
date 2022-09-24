package swu.xl.linkgame.LinkGame.SelfView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.List;

import swu.xl.linkgame.Constant.Constant;
import swu.xl.linkgame.LinkGame.Model.AnimalPoint;
import swu.xl.linkgame.LinkGame.Model.LinkInfo;
import swu.xl.linkgame.LinkGame.Utils.CustomPaint;
import swu.xl.linkgame.LinkGame.Utils.LinkUtil;

public class XLRelativeLayout extends RelativeLayout {
    // 点的信息
    private LinkInfo linkInfo;

    private CustomPaint customPaint;

    public XLRelativeLayout(Context context) {
        super(context);
        // 让onDraw方法执行
        setWillNotDraw(false);
    }

    public XLRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 让onDraw方法执行
        setWillNotDraw(false);
    }

    // setter getter
    public LinkInfo getLinkInfo() {
        return linkInfo;
    }

    public void setLinkInfo(LinkInfo linkInfo) {
        this.linkInfo = linkInfo;
        customPaint = new CustomPaint();
        invalidate();
    }

    // 重绘
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // linkInfo不为空
        if (linkInfo != null) {

            Log.d(Constant.TAG, "刷新界面");

            // 获取点数据
            List<AnimalPoint> points = linkInfo.getPoints();

            // 判断linkInfo是否有数据
            if (points.size() != 0) {
                // 画路径
                for (int i = 0; i < points.size() - 1; i++) {
                    // 转换坐标
                    AnimalPoint realPoint1 = LinkUtil.getRealAnimalPoint(
                            points.get(i),
                            getContext()
                    );
                    AnimalPoint realPoint2 = LinkUtil.getRealAnimalPoint(
                            points.get(i + 1),
                            getContext()
                    );

                    // 测试
                    Log.d(Constant.TAG, points.get(i).toString() + " " + realPoint1);
                    Log.d(Constant.TAG, points.get(i + 1).toString() + " " + realPoint2);

                    // 画闪电
                    customPaint.drawLightning(realPoint1.x, realPoint1.y, realPoint2.x, realPoint2.y, 5, canvas);
                    customPaint.drawLightningBold(realPoint1.x, realPoint1.y, realPoint2.x, realPoint2.y, 5, canvas);
                }
            }
        }
    }
}
