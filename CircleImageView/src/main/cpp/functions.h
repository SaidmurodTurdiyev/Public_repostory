//
// Created by User on 10/29/2021.
//

#ifndef NDK_DEMO_FUNCTIONS_H
#define NDK_DEMO_FUNCTIONS_H

#define fun(TYPE, NAME, ...) JNIEXPORT TYPE JNICALL PACKAGE(NAME)(JNIEnv *env, jobject thiz, ## __VA_ARGS__)
#define nativeScope extern "C"

#endif //NDK_DEMO_FUNCTIONS_H
