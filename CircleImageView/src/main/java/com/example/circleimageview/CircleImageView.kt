package com.example.circleimageview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import java.lang.StrictMath.min

class CircleImageView @JvmOverloads constructor(context: Context, attribut: AttributeSet, defStyle: Int = 0) : View(context, attribut, defStyle) {

    private var paint = Paint()
    private var drawable: Drawable? = null
    private var borderWidth: Float = 0.0F
    private var borderColor: Int = 0
    private var radious = 0

    init {
        val typedArray = context.obtainStyledAttributes(attribut, R.styleable.CircleImageView)
        drawable = typedArray.getDrawable(R.styleable.CircleImageView_src)
        borderWidth = typedArray.getDimension(R.styleable.CircleImageView_borderWidth, borderWidth)
        borderColor = typedArray.getColor(R.styleable.CircleImageView_borderColor, Color.TRANSPARENT)
        typedArray.recycle()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val start = System.currentTimeMillis()
        val btm = drawable?.toBitmap(width, height, Bitmap.Config.ARGB_8888)
        if (btm != null) {
            radious = min(btm.height / 2, btm.width / 2)
            val pix = IntArray(btm.width * btm.height)
            btm.getPixels(pix, 0, btm.width, 0, 0, btm.width, btm.height)
            val bitmap = Bitmap.createBitmap(btm.width, btm.height, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(myCircle(pix, btm.width, btm.height, radious, borderColor, borderWidth), 0, btm.width, 0, 0, btm.width, btm.height);
            canvas.drawBitmap(bitmap, 0F, 0F, paint)
            Log.d("TTT", "${System.currentTimeMillis() - start}")
        }
    }

    external fun myCircle(data: IntArray, width: Int, height: Int, radius: Int, borderColor: Int, borderWidth: Float): IntArray

    companion object {
        init {
            System.loadLibrary("utils")
        }
    }
}