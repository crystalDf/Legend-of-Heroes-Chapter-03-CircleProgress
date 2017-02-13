package com.star.circleprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;


public class CircleProgressView extends View {

    private int mMeasureWidth;
    private int mMeasureHeight;

    private float mCircleXY;
    private float mRadius;
    private Paint mCirclePaint;

    private RectF mArcRectF;

    private float mSweepAngle;
    private Paint mArcPaint;

    private String mText;
    private int mTextSize;
    private Paint mTextPaint;

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(mMeasureWidth, mMeasureHeight);

        init();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        canvas.drawArc(mArcRectF, 0, mSweepAngle, false, mArcPaint);
        canvas.drawText(mText, 0, mText.length(), mCircleXY, mCircleXY + mTextSize / 4, mTextPaint);

        invalidate();
    }

    private void init() {

        float length = Math.min(mMeasureWidth, mMeasureHeight);

        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(ContextCompat.getColor(getContext(),
                android.R.color.holo_blue_bright));

        float arcStart = (float) (length * 0.1);
        float arcEnd = (float) (length * 0.9);

        mArcRectF = new RectF(arcStart, arcStart, arcEnd, arcEnd);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_bright));
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(arcStart);

        mText = "Android";
        mTextSize = 50;

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }

    public void setSweepAngle(float sweepAngle) {
        mSweepAngle = (sweepAngle == 0 ? 90 : sweepAngle);

        invalidate();
    }

}
