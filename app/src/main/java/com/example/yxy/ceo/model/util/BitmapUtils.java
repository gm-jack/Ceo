package com.example.yxy.ceo.model.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * Created by yxy on 2016/8/31.
 */

public class BitmapUtils {

    private Context mContext;

    public BitmapUtils(Context context) {
        mContext = context;
    }

    public Bitmap decodeSampleBitmapFromResource(int width, int height, int resource) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mContext.getResources(), resource, options);

        options.inSampleSize = caculateInSampleSize(options, width, height);

        //使用获取到的inSampleSize再次解析图片,加载内存
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), resource, options);
        return bitmap;
    }

    /**
     * 根据图片显示的宽和高对图片压缩
     */
    public Bitmap decodeSampleBitmapFromPath(int width, int height, String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //获取图片的宽和高，没有将图片加载内存中
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = caculateInSampleSize(options, width, height);

        //使用获取到的inSampleSize再次解析图片,加载内存
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 根据需求的宽和高以及图片世纪的宽和高计算samplesize
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;
        //如果图片的宽度大于需求宽度，或者图片高度大于需求高度，压缩
        if (width > reqWidth || height > reqHeight) {
            //获取宽高压缩比例
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(height * 1.0f / reqHeight);
            //取比例最大值，压缩图片最小
            inSampleSize = Math.max(widthRadio, heightRadio);
        }
        return inSampleSize;
    }

    /**
     * 获得图片需要显示的大小(只可以返回一个参数，建立ImageSize对象)
     * 根据imageview获取适当的宽和高（压缩）
     * 当前最小兼容API16，需要通过反射获取ImageView的width和height最大值
     *
     * @param imageView
     */
    private ImageSize getImageVeiwSize(ImageView imageView) {
        ImageSize size = new ImageSize();
        //获取屏幕的宽度
        DisplayMetrics metrics = imageView.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        //获取imageview的实际宽度
        int width = params.width == ViewGroup.LayoutParams.WRAP_CONTENT ? 0 : imageView.getWidth();
        if (width <= 0) {
            //获取imageview在layout中声明的宽度
            width = params.width;
        }
        //如果声明为wrap_content或者match_parent，获取不到imagview的宽度
        if (width <= 0) {
            //检查最大值
//            width = imageView.getMaxWidth();
            width = getImageViewFromFieldValue(imageView, "mMaxWidth");//反射获取Width最大值
        }
        if (width <= 0) {
            //获取屏幕的宽度
            width = metrics.widthPixels;
        }

        //获取imageview的实际高度
        int height = params.height == ViewGroup.LayoutParams.WRAP_CONTENT ? 0 : imageView.getHeight();
        if (height <= 0) {
            //获取imageview在layout中声明的高度
            height = params.height;
        }
        //如果声明为wrap_content或者match_parent，获取不到imagview的高度
        if (height <= 0) {
            //检查最大值
            //height = imageView.getMaxHeight();
            height = getImageViewFromFieldValue(imageView, "mMaxHeight");//反射获取Height最大值
        }
        if (height <= 0) {
            //获取屏幕的高度
            height = metrics.heightPixels;
        }

        size.width = width;
        size.height = height;
        return size;
    }

    /**
     * 通过反射获取ImageView的某个属性值
     *
     * @param obj
     * @param fieldName
     * @return
     */
    private static int getImageViewFromFieldValue(Object obj, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            //暴力破解私有属性
            field.setAccessible(true);
            int fieldValue = field.getInt(obj);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private class ImageSize {
        int width;
        int height;
    }


}
