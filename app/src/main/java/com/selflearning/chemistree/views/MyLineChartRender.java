package com.selflearning.chemistree.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//public class MyLineChartRender extends LineChartRenderer {
//    public MyLineChartRender(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
//        super(chart, animator, viewPortHandler);
//    }
//
//    @Override
//    public void drawValues(Canvas c) {
//        if (isDrawingValuesAllowed(mChart)) {
//
//            List<ILineDataSet> dataSets = mChart.getLineData().getDataSets();
//            for (int i = 0; i < dataSets.size(); i++) {
//
//                ILineDataSet dataSet = dataSets.get(i);
//                if (!shouldDrawValues(dataSet) || dataSet.getEntryCount() < 1)
//                    continue;
//
//                // apply the text-styling defined by the DataSet
//                applyValueTextStyle(dataSet);
//
//                Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());
//
//                // make sure the values do not interfear with the circles
//                int valOffset = (int) (dataSet.getCircleRadius() * 3f);
//
//                if (!dataSet.isDrawCirclesEnabled())
//                    valOffset = valOffset / 2;
//
//                mXBounds.set(mChart, dataSet);
//
//                float[] positions = trans.generateTransformedValuesLine(dataSet, mAnimator.getPhaseX(), mAnimator
//                        .getPhaseY(), mXBounds.min, mXBounds.max);
//                ValueFormatter formatter = dataSet.getValueFormatter();
//
//                MPPointF iconsOffset = MPPointF.getInstance(dataSet.getIconsOffset());
//                iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
//                iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
//
//                for (int j = 0; j < positions.length; j += 2) {
//
//                    float x = positions[j];
//                    float y = positions[j + 1];
//
//                    if (!mViewPortHandler.isInBoundsRight(x))
//                        break;
//
//                    if (!mViewPortHandler.isInBoundsLeft(x) || !mViewPortHandler.isInBoundsY(y))
//                        continue;
//
//                    Entry entry = dataSet.getEntryForIndex(j / 2 + mXBounds.min);
//
//                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//                    float f = paint.measureText(formatter.getPointLabel(entry));
//
//
//
//                    if (dataSet.isDrawValuesEnabled() && j == positions.length-2) {
//                        drawValue(c, formatter.getPointLabel(entry), x + valOffset + f, y, dataSet.getValueTextColor(j / 2));
////                        drawSecondValue(c, "" + posit, x + valOffset + f, y);
//                    }
//
//                    if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
//
//                        Drawable icon = entry.getIcon();
//
//                        Utils.drawImage(
//                                c,
//                                icon,
//                                (int)(x + iconsOffset.x),
//                                (int)(y + iconsOffset.y),
//                                icon.getIntrinsicWidth(),
//                                icon.getIntrinsicHeight());
//                    }
//                }
//
//                MPPointF.recycleInstance(iconsOffset);
//            }
//        }
//    }
//
//    @Override
//    public void drawValue(Canvas c, String valueText, float x, float y, int color) {
//        mValuePaint.setTextAlign(Paint.Align.LEFT);
//        super.drawValue(c, valueText, x, y, color);
//    }
//
//    public void drawSecondValue(Canvas c, String valueText, float x, float y){
//        mValuePaint.setColor(Color.GRAY);
//        mValuePaint.setTextSize(25f);
//        c.drawText(valueText, x, y + 35f, mValuePaint);
//    }
//
//
//    private HashMap<IDataSet, MyLineChartRender.DataSetImageCache> mImageCaches = new HashMap<>();
//    private float[] mCirclesBuffer = new float[2];
//    @Override
//    protected void drawCircles(Canvas c) {
//
//        mRenderPaint.setStyle(Paint.Style.FILL);
//
//        float phaseY = mAnimator.getPhaseY();
//
//        mCirclesBuffer[0] = 0;
//        mCirclesBuffer[1] = 0;
//
//        List<ILineDataSet> dataSets = mChart.getLineData().getDataSets();
//
//        for (int i = 0; i < dataSets.size(); i++) {
//
//            ILineDataSet dataSet = dataSets.get(i);
//
//            if (!dataSet.isVisible() || !dataSet.isDrawCirclesEnabled() ||
//                    dataSet.getEntryCount() == 0)
//                continue;
//
//            mCirclePaintInner.setColor(dataSet.getCircleHoleColor());
//
//            Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());
//
//            mXBounds.set(mChart, dataSet);
//
//            float circleRadius = dataSet.getCircleRadius();
//            float circleHoleRadius = dataSet.getCircleHoleRadius();
//            boolean drawCircleHole = dataSet.isDrawCircleHoleEnabled() &&
//                    circleHoleRadius < circleRadius &&
//                    circleHoleRadius > 0.f;
//            boolean drawTransparentCircleHole = drawCircleHole &&
//                    dataSet.getCircleHoleColor() == ColorTemplate.COLOR_NONE;
//
//            DataSetImageCache imageCache;
//
//            if (mImageCaches.containsKey(dataSet)) {
//                imageCache = mImageCaches.get(dataSet);
//            } else {
//                imageCache = new DataSetImageCache();
//                mImageCaches.put(dataSet, imageCache);
//            }
//
//            boolean changeRequired = imageCache.init(dataSet);
//
//            // only fill the cache with new bitmaps if a change is required
//            if (changeRequired) {
//                imageCache.fill(dataSet, drawCircleHole, drawTransparentCircleHole);
//            }
//
//            int boundsRangeCount = mXBounds.range + mXBounds.min;
//
//            for (int j = mXBounds.min; j <= boundsRangeCount; j++) {
//
//                Entry e = dataSet.getEntryForIndex(j);
//
//                if (e == null) break;
//
//                mCirclesBuffer[0] = e.getX();
//                mCirclesBuffer[1] = e.getY() * phaseY;
//
//                trans.pointValuesToPixel(mCirclesBuffer);
//
//                if (!mViewPortHandler.isInBoundsRight(mCirclesBuffer[0]))
//                    break;
//
//                if (!mViewPortHandler.isInBoundsLeft(mCirclesBuffer[0]) ||
//                        !mViewPortHandler.isInBoundsY(mCirclesBuffer[1]))
//                    continue;
//
//                Bitmap circleBitmap = imageCache.getBitmap(j);
//
//                if (circleBitmap != null) {
//                    c.drawBitmap(circleBitmap, mCirclesBuffer[0] - circleRadius, mCirclesBuffer[1] - circleRadius, null);
//                }
//            }
//        }
//    }
//
//    private class DataSetImageCache {
//
//        private Path mCirclePathBuffer = new Path();
//
//        private Bitmap[] circleBitmaps;
//
//        /**
//         * Sets up the cache, returns true if a change of cache was required.
//         *
//         * @param set
//         * @return
//         */
//        protected boolean init(ILineDataSet set) {
//
//            int size = set.getCircleColorCount();
//            boolean changeRequired = false;
//
//            if (circleBitmaps == null) {
//                circleBitmaps = new Bitmap[size];
//                changeRequired = true;
//            } else if (circleBitmaps.length != size) {
//                circleBitmaps = new Bitmap[size];
//                changeRequired = true;
//            }
//
//            return changeRequired;
//        }
//
//        /**
//         * Fills the cache with bitmaps for the given dataset.
//         *
//         * @param set
//         * @param drawCircleHole
//         * @param drawTransparentCircleHole
//         */
//        protected void fill(ILineDataSet set, boolean drawCircleHole, boolean drawTransparentCircleHole) {
//
//            int colorCount = set.getCircleColorCount();
//            float circleRadius = set.getCircleRadius();
//            float circleHoleRadius = set.getCircleHoleRadius();
//
//            for (int i = 0; i < colorCount; i++) {
//                if(set.getEntryForIndex(i).getY() == 0) continue;
//
//                Bitmap.Config conf = Bitmap.Config.ARGB_4444;
//                Bitmap circleBitmap = Bitmap.createBitmap((int) (circleRadius * 2.1), (int) (circleRadius * 2.1), conf);
//
//                Canvas canvas = new Canvas(circleBitmap);
//                circleBitmaps[i] = circleBitmap;
//                mRenderPaint.setColor(set.getCircleColor(i));
//
//                if (i == colorCount - 1) {
//                    mRenderPaint.setColor(Color.WHITE);
//                    canvas.drawCircle(
//                            circleRadius,
//                            circleRadius,
//                            circleRadius,
//                            mRenderPaint);
//                } else {
//                    canvas.drawCircle(
//                            circleRadius,
//                            circleRadius,
//                            circleRadius,
//                            mRenderPaint);
//
//                    if (drawCircleHole) {
//                        canvas.drawCircle(
//                                circleRadius,
//                                circleRadius,
//                                circleHoleRadius,
//                                mCirclePaintInner);
//                    }
//                }
//            }
//        }
//
//        /**
//         * Returns the cached Bitmap at the given index.
//         *
//         * @param index
//         * @return
//         */
//        protected Bitmap getBitmap(int index) {
//            return circleBitmaps[index % circleBitmaps.length];
//        }
//    }
//}
