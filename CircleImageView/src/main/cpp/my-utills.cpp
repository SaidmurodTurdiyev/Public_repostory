#include <jni.h>
#include "functions.h"

#define PACKAGE(NAME) Java_com_example_circleimageview_CircleImageView_##NAME
//
// Created by User on 10/29/2021.
//

nativeScope {
int sqr(int a) { return a * a; }
int min(int a, int b) { return a > b ? b : a; }
fun(jintArray, myCircle, jintArray data, jint width, jint height, jint radius, jint borderColor, jfloat borderWidth) {

    int index;
    int x, y, centerX = width / 2, centerY = height / 2;
    int border=borderWidth;
    jint *buffer = env->GetIntArrayElements(data, NULL);
    env->ReleaseIntArrayElements(data, buffer, JNI_ABORT);
    for (y = 0; y <= height - 1; y++) {
        for (x = 0; x <= width - 1; x++) {
            index = y * width + x;
            int sum = sqr(x - centerX) + sqr(y - centerY);
            if (sum > sqr(radius)) {
                buffer[index] = 0;
            } else if ( sum >= sqr(radius-border))
                buffer[index] = borderColor;
        }
    }

    env->SetIntArrayRegion(data, 0, width * height, buffer);
    return data;
}
};




