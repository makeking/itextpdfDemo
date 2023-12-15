package com.bete.pdfutilks.utils;////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package com.utils;
//
//import android.app.Activity;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.ContextWrapper;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.content.pm.ResolveInfo;
//import android.graphics.drawable.Drawable;
//import android.os.Build.VERSION;
//import android.os.Bundle;
//import android.support.annotation.AnimRes;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityOptionsCompat;
//import android.support.v4.util.Pair;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//
//
//import java.util.Iterator;
//import java.util.List;
//
//public final class ActivityUtils {
//    private ActivityUtils() {
//        throw new UnsupportedOperationException("u can't instantiate me...");
//    }
//
//    public static AppCompatActivity getActivityByView(@NonNull View view) {
//        if (view == null) {
//            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            for(Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {
//                if (context instanceof AppCompatActivity) {
//                    return (AppCompatActivity)context;
//                }
//            }
//
//            return null;
//        }
//    }
//
//    public static boolean isActivityExists(@NonNull String pkg, @NonNull String cls) {
//        if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Intent intent = new Intent();
//            intent.setClassName(pkg, cls);
//            return Utils.getApp().getPackageManager().resolveActivity(intent, 0) != null && intent.resolveActivity(Utils.getApp().getPackageManager()) != null && Utils.getApp().getPackageManager().queryIntentActivities(intent, 0).size() != 0;
//        }
//    }
//
//    public static void startActivity(@NonNull Class<? extends AppCompatActivity> clz) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivity(context, null, context.getPackageName(), clz.getName(), null);
//        }
//    }
//
//    public static void startActivity(@NonNull Class<? extends AppCompatActivity> clz, Bundle options) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivity(context, null, context.getPackageName(), clz.getName(), options);
//        }
//    }
//
//    public static void startActivity(@NonNull Class<? extends AppCompatActivity> clz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivity(context, null, context.getPackageName(), clz.getName(), getOptionsBundle(context, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16 && context instanceof AppCompatActivity) {
//                ((AppCompatActivity)context).overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, null, activity.getPackageName(), clz.getName(), null);
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, Bundle options) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, null, activity.getPackageName(), clz.getName(), options);
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, View... sharedElements) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, null, activity.getPackageName(), clz.getName(), getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, null, activity.getPackageName(), clz.getName(), getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull Class<? extends AppCompatActivity> clz) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivity(context, extras, context.getPackageName(), clz.getName(), null);
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull Class<? extends AppCompatActivity> clz, @NonNull Bundle options) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (options == null) {
//            throw new NullPointerException("Argument 'options' of type Bundle (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivity(context, extras, context.getPackageName(), clz.getName(), options);
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull Class<? extends AppCompatActivity> clz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivity(context, extras, context.getPackageName(), clz.getName(), getOptionsBundle(context, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16 && context instanceof AppCompatActivity) {
//                ((AppCompatActivity)context).overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, extras, activity.getPackageName(), clz.getName(), null);
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, @NonNull Bundle options) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (options == null) {
//            throw new NullPointerException("Argument 'options' of type Bundle (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, extras, activity.getPackageName(), clz.getName(), options);
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, View... sharedElements) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, extras, activity.getPackageName(), clz.getName(), getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, extras, activity.getPackageName(), clz.getName(), getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivity(@NonNull String pkg, @NonNull String cls) {
//        if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(Utils.getTopActivityOrApp(), null, pkg, cls, null);
//        }
//    }
//
//    public static void startActivity(@NonNull String pkg, @NonNull String cls, Bundle options) {
//        if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(Utils.getTopActivityOrApp(), null, pkg, cls, options);
//        }
//    }
//
//    public static void startActivity(@NonNull String pkg, @NonNull String cls, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivity(context, null, pkg, cls, getOptionsBundle(context, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16 && context instanceof AppCompatActivity) {
//                ((AppCompatActivity)context).overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, null, pkg, cls, null);
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, Bundle options) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, null, pkg, cls, options);
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, View... sharedElements) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, null, pkg, cls, getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, null, pkg, cls, getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull String pkg, @NonNull String cls) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(Utils.getTopActivityOrApp(), extras, pkg, cls, null);
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull String pkg, @NonNull String cls, Bundle options) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(Utils.getTopActivityOrApp(), extras, pkg, cls, options);
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull String pkg, @NonNull String cls, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivity(context, extras, pkg, cls, getOptionsBundle(context, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16 && context instanceof AppCompatActivity) {
//                ((AppCompatActivity)context).overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, extras, pkg, cls, null);
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, Bundle options) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, extras, pkg, cls, options);
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, View... sharedElements) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, extras, pkg, cls, getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivity(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(activity, extras, pkg, cls, getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static boolean startActivity(@NonNull Intent intent) {
//        if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            return startActivity(intent, Utils.getTopActivityOrApp(), null);
//        }
//    }
//
//    public static boolean startActivity(@NonNull Intent intent, @NonNull Bundle options) {
//        if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (options == null) {
//            throw new NullPointerException("Argument 'options' of type Bundle (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            return startActivity(intent, Utils.getTopActivityOrApp(), options);
//        }
//    }
//
//    public static boolean startActivity(@NonNull Intent intent, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            boolean isSuccess = startActivity(intent, context, getOptionsBundle(context, enterAnim, exitAnim));
//            if (isSuccess && VERSION.SDK_INT < 16 && context instanceof AppCompatActivity) {
//                ((AppCompatActivity)context).overridePendingTransition(enterAnim, exitAnim);
//            }
//
//            return isSuccess;
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull Intent intent) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(intent, activity, null);
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull Intent intent, Bundle options) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(intent, activity, options);
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull Intent intent, View... sharedElements) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(intent, activity, getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivity(@NonNull AppCompatActivity activity, @NonNull Intent intent, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivity(intent, activity, getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, int requestCode) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, null, activity.getPackageName(), clz.getName(), requestCode, null);
//        }
//    }
//
//    public static void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, int requestCode, Bundle options) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, null, activity.getPackageName(), clz.getName(), requestCode, options);
//        }
//    }
//
//    public static void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, int requestCode, View... sharedElements) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, null, activity.getPackageName(), clz.getName(), requestCode, getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, int requestCode, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, null, activity.getPackageName(), clz.getName(), requestCode, getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivityForResult(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, int requestCode) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, extras, activity.getPackageName(), clz.getName(), requestCode, null);
//        }
//    }
//
//    public static void startActivityForResult(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, int requestCode, @NonNull Bundle options) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (options == null) {
//            throw new NullPointerException("Argument 'options' of type Bundle (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, extras, activity.getPackageName(), clz.getName(), requestCode, options);
//        }
//    }
//
//    public static void startActivityForResult(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, int requestCode, View... sharedElements) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, extras, activity.getPackageName(), clz.getName(), requestCode, getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivityForResult(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull Class<? extends AppCompatActivity> clz, int requestCode, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, extras, activity.getPackageName(), clz.getName(), requestCode, getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivityForResult(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, int requestCode) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, extras, pkg, cls, requestCode, null);
//        }
//    }
//
//    public static void startActivityForResult(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, int requestCode, Bundle options) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, extras, pkg, cls, requestCode, options);
//        }
//    }
//
//    public static void startActivityForResult(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, int requestCode, View... sharedElements) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, extras, pkg, cls, requestCode, getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivityForResult(@NonNull Bundle extras, @NonNull AppCompatActivity activity, @NonNull String pkg, @NonNull String cls, int requestCode, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (extras == null) {
//            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (cls == null) {
//            throw new NullPointerException("Argument 'cls' of type String (#3 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(activity, extras, pkg, cls, requestCode, getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Intent intent, int requestCode) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(intent, activity, requestCode, null);
//        }
//    }
//
//    public static void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Intent intent, int requestCode, Bundle options) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(intent, activity, requestCode, options);
//        }
//    }
//
//    public static void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Intent intent, int requestCode, View... sharedElements) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(intent, activity, requestCode, getOptionsBundle(activity, sharedElements));
//        }
//    }
//
//    public static void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Intent intent, int requestCode, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intent == null) {
//            throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivityForResult(intent, activity, requestCode, getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivities(@NonNull Intent[] intents) {
//        if (intents == null) {
//            throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivities(intents, Utils.getTopActivityOrApp(), null);
//        }
//    }
//
//    public static void startActivities(@NonNull Intent[] intents, Bundle options) {
//        if (intents == null) {
//            throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivities(intents, Utils.getTopActivityOrApp(), options);
//        }
//    }
//
//    public static void startActivities(@NonNull Intent[] intents, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (intents == null) {
//            throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Context context = Utils.getTopActivityOrApp();
//            startActivities(intents, context, getOptionsBundle(context, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16 && context instanceof AppCompatActivity) {
//                ((AppCompatActivity)context).overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startActivities(@NonNull AppCompatActivity activity, @NonNull Intent[] intents) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intents == null) {
//            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivities(intents, activity, null);
//        }
//    }
//
//    public static void startActivities(@NonNull AppCompatActivity activity, @NonNull Intent[] intents, Bundle options) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intents == null) {
//            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivities(intents, activity, options);
//        }
//    }
//
//    public static void startActivities(@NonNull AppCompatActivity activity, @NonNull Intent[] intents, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else if (intents == null) {
//            throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            startActivities(intents, activity, getOptionsBundle(activity, enterAnim, exitAnim));
//            if (VERSION.SDK_INT < 16) {
//                activity.overridePendingTransition(enterAnim, exitAnim);
//            }
//
//        }
//    }
//
//    public static void startHomeActivity() {
//        Intent homeIntent = new Intent("android.intent.action.MAIN");
//        homeIntent.addCategory("android.intent.category.HOME");
//        startActivity(homeIntent);
//    }
//
//    public static List<Activity> getActivityList() {
//        return Utils.getActivityList();
//    }
//
//    public static String getLauncherActivity() {
//        return getLauncherActivity(Utils.getApp().getPackageName());
//    }
//
//    public static String getLauncherActivity(@NonNull String pkg) {
//        if (pkg == null) {
//            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            Intent intent = new Intent("android.intent.action.MAIN", null);
//            intent.addCategory("android.intent.category.LAUNCHER");
//            intent.addFlags(268435456);
//            PackageManager pm = Utils.getApp().getPackageManager();
//            List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
//            Iterator var4 = info.iterator();
//
//            ResolveInfo aInfo;
//            do {
//                if (!var4.hasNext()) {
//                    return "no " + pkg;
//                }
//
//                aInfo = (ResolveInfo)var4.next();
//            } while(!aInfo.activityInfo.packageName.equals(pkg));
//
//            return aInfo.activityInfo.name;
//        }
//    }
//
//    public static Activity getTopActivity() {
//        return Utils.getActivityLifecycle().getTopActivity();
//    }
//
//    public static boolean isActivityExistsInStack(@NonNull AppCompatActivity activity) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<Activity> activities = Utils.getActivityList();
//            Iterator var2 = activities.iterator();
//
//            AppCompatActivity aActivity;
//            do {
//                if (!var2.hasNext()) {
//                    return false;
//                }
//
//                aActivity = (AppCompatActivity)var2.next();
//            } while(!aActivity.equals(activity));
//
//            return true;
//        }
//    }
//
//    public static boolean isActivityExistsInStack(@NonNull Class<? extends AppCompatActivity> clz) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<Activity> activities = Utils.getActivityList();
//            Iterator var2 = activities.iterator();
//
//            Activity aActivity;
//            do {
//                if (!var2.hasNext()) {
//                    return false;
//                }
//
//                aActivity = (Activity)var2.next();
//            } while(!aActivity.getClass().equals(clz));
//
//            return true;
//        }
//    }
//
//    public static void finishActivity(@NonNull Activity activity) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            finishActivity(activity, false);
//        }
//    }
//
//    public static void finishActivity(@NonNull Activity activity, boolean isLoadAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            activity.finish();
//            if (!isLoadAnim) {
//                activity.overridePendingTransition(0, 0);
//            }
//
//        }
//    }
//
//    public static void finishActivity(@NonNull Activity activity, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            activity.finish();
//            activity.overridePendingTransition(enterAnim, exitAnim);
//        }
//    }
//
//    public static void finishActivity(@NonNull Class<? extends AppCompatActivity> clz) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            finishActivity(clz, false);
//        }
//    }
//
//    public static void finishActivity(@NonNull Class<? extends AppCompatActivity> clz, boolean isLoadAnim) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<Activity> activities = Utils.getActivityList();
//            Iterator var3 = activities.iterator();
//
//            while(var3.hasNext()) {
//                AppCompatActivity activity = (AppCompatActivity)var3.next();
//                if (activity.getClass().equals(clz)) {
//                    activity.finish();
//                    if (!isLoadAnim) {
//                        activity.overridePendingTransition(0, 0);
//                    }
//                }
//            }
//
//        }
//    }
//
//    public static void finishActivity(@NonNull Class<? extends AppCompatActivity> clz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<AppCompatActivity> activities = Utils.getActivityList();
//            Iterator var4 = activities.iterator();
//
//            while(var4.hasNext()) {
//                AppCompatActivity activity = (AppCompatActivity)var4.next();
//                if (activity.getClass().equals(clz)) {
//                    activity.finish();
//                    activity.overridePendingTransition(enterAnim, exitAnim);
//                }
//            }
//
//        }
//    }
//
//    public static boolean finishToActivity(@NonNull AppCompatActivity activity, boolean isIncludeSelf) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            return finishToActivity(activity, isIncludeSelf, false);
//        }
//    }
//
//    public static boolean finishToActivity(@NonNull AppCompatActivity activity, boolean isIncludeSelf, boolean isLoadAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<Activity> activities = Utils.getActivityList();
//
//            for(int i = activities.size() - 1; i >= 0; --i) {
//                Activity aActivity = activities.get(i);
//                if (aActivity.equals(activity)) {
//                    if (isIncludeSelf) {
//                        finishActivity(aActivity, isLoadAnim);
//                    }
//
//                    return true;
//                }
//
//                finishActivity(aActivity, isLoadAnim);
//            }
//
//            return false;
//        }
//    }
//
//    public static boolean finishToActivity(@NonNull AppCompatActivity activity, boolean isIncludeSelf, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<Activity> activities = Utils.getActivityList();
//
//            for(int i = activities.size() - 1; i >= 0; --i) {
//                Activity aActivity = activities.get(i);
//                if (aActivity.equals(activity)) {
//                    if (isIncludeSelf) {
//                        finishActivity(aActivity, enterAnim, exitAnim);
//                    }
//
//                    return true;
//                }
//
//                finishActivity(aActivity, enterAnim, exitAnim);
//            }
//
//            return false;
//        }
//    }
//
//    public static boolean finishToActivity(@NonNull Class<? extends AppCompatActivity> clz, boolean isIncludeSelf) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            return finishToActivity(clz, isIncludeSelf, false);
//        }
//    }
//
//    public static boolean finishToActivity(@NonNull Class<? extends AppCompatActivity> clz, boolean isIncludeSelf, boolean isLoadAnim) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<AppCompatActivity> activities = Utils.getActivityList();
//
//            for(int i = activities.size() - 1; i >= 0; --i) {
//                AppCompatActivity aActivity = activities.get(i);
//                if (aActivity.getClass().equals(clz)) {
//                    if (isIncludeSelf) {
//                        finishActivity(aActivity, isLoadAnim);
//                    }
//
//                    return true;
//                }
//
//                finishActivity(aActivity, isLoadAnim);
//            }
//
//            return false;
//        }
//    }
//
//    public static boolean finishToActivity(@NonNull Class<? extends AppCompatActivity> clz, boolean isIncludeSelf, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<AppCompatActivity> activities = Utils.getActivityList();
//
//            for(int i = activities.size() - 1; i >= 0; --i) {
//                AppCompatActivity aActivity = activities.get(i);
//                if (aActivity.getClass().equals(clz)) {
//                    if (isIncludeSelf) {
//                        finishActivity(aActivity, enterAnim, exitAnim);
//                    }
//
//                    return true;
//                }
//
//                finishActivity(aActivity, enterAnim, exitAnim);
//            }
//
//            return false;
//        }
//    }
//
//    public static void finishOtherActivities(@NonNull Class<? extends AppCompatActivity> clz) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            finishOtherActivities(clz, false);
//        }
//    }
//
//    public static void finishOtherActivities(@NonNull Class<? extends AppCompatActivity> clz, boolean isLoadAnim) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<AppCompatActivity> activities = Utils.getActivityList();
//
//            for(int i = activities.size() - 1; i >= 0; --i) {
//                AppCompatActivity activity = activities.get(i);
//                if (!activity.getClass().equals(clz)) {
//                    finishActivity(activity, isLoadAnim);
//                }
//            }
//
//        }
//    }
//
//    public static void finishOtherActivities(@NonNull Class<? extends AppCompatActivity> clz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            List<AppCompatActivity> activities = Utils.getActivityList();
//
//            for(int i = activities.size() - 1; i >= 0; --i) {
//                AppCompatActivity activity = activities.get(i);
//                if (!activity.getClass().equals(clz)) {
//                    finishActivity(activity, enterAnim, exitAnim);
//                }
//            }
//
//        }
//    }
//
//    public static void finishAllActivities() {
//        finishAllActivities(false);
//    }
//
//    public static void finishAllActivities(boolean isLoadAnim) {
//        List<AppCompatActivity> activityList = Utils.getActivityList();
//
//        for(int i = activityList.size() - 1; i >= 0; --i) {
//            AppCompatActivity activity = activityList.get(i);
//            activity.finish();
//            if (!isLoadAnim) {
//                activity.overridePendingTransition(0, 0);
//            }
//        }
//
//    }
//
//    public static void finishAllActivities(@AnimRes int enterAnim, @AnimRes int exitAnim) {
//        List<AppCompatActivity> activityList = Utils.getActivityList();
//
//        for(int i = activityList.size() - 1; i >= 0; --i) {
//            AppCompatActivity activity = activityList.get(i);
//            activity.finish();
//            activity.overridePendingTransition(enterAnim, exitAnim);
//        }
//
//    }
//
//    public static void finishAllActivitiesExceptNewest() {
//        finishAllActivitiesExceptNewest(false);
//    }
//
//    public static void finishAllActivitiesExceptNewest(boolean isLoadAnim) {
//        List<AppCompatActivity> activities = Utils.getActivityList();
//
//        for(int i = activities.size() - 2; i >= 0; --i) {
//            finishActivity(activities.get(i), isLoadAnim);
//        }
//
//    }
//
//    public static void finishAllActivitiesExceptNewest(@AnimRes int enterAnim, @AnimRes int exitAnim) {
//        List<Activity> activities = Utils.getActivityList();
//
//        for(int i = activities.size() - 2; i >= 0; --i) {
//            finishActivity(activities.get(i), enterAnim, exitAnim);
//        }
//
//    }
//
//    public static Drawable getActivityIcon(@NonNull AppCompatActivity activity) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            return getActivityIcon(activity.getComponentName());
//        }
//    }
//
//    public static Drawable getActivityIcon(@NonNull Class<? extends AppCompatActivity> clz) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            return getActivityIcon(new ComponentName(Utils.getApp(), clz));
//        }
//    }
//
//    public static Drawable getActivityIcon(@NonNull ComponentName activityName) {
//        if (activityName == null) {
//            throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            PackageManager pm = Utils.getApp().getPackageManager();
//
//            try {
//                return pm.getActivityIcon(activityName);
//            } catch (NameNotFoundException var3) {
//                var3.printStackTrace();
//                return null;
//            }
//        }
//    }
//
//    public static Drawable getActivityLogo(@NonNull AppCompatActivity activity) {
//        if (activity == null) {
//            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            return getActivityLogo(activity.getComponentName());
//        }
//    }
//
//    public static Drawable getActivityLogo(@NonNull Class<? extends AppCompatActivity> clz) {
//        if (clz == null) {
//            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            return getActivityLogo(new ComponentName(Utils.getApp(), clz));
//        }
//    }
//
//    public static Drawable getActivityLogo(@NonNull ComponentName activityName) {
//        if (activityName == null) {
//            throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
//        } else {
//            PackageManager pm = Utils.getApp().getPackageManager();
//
//            try {
//                return pm.getActivityLogo(activityName);
//            } catch (NameNotFoundException var3) {
//                var3.printStackTrace();
//                return null;
//            }
//        }
//    }
//
//    private static void startActivity(Context context, Bundle extras, String pkg, String cls, Bundle options) {
//        Intent intent = new Intent("android.intent.action.VIEW");
//        if (extras != null) {
//            intent.putExtras(extras);
//        }
//
//        intent.setComponent(new ComponentName(pkg, cls));
//        startActivity(intent, context, options);
//    }
//
//    private static boolean startActivity(Intent intent, Context context, Bundle options) {
//        if (!isIntentAvailable(intent)) {
//            Log.e("ActivityUtils", "intent is unavailable");
//            return false;
//        } else {
//            if (!(context instanceof AppCompatActivity)) {
//                intent.addFlags(268435456);
//            }
//
//            if (options != null && VERSION.SDK_INT >= 16) {
//                context.startActivity(intent, options);
//            } else {
//                context.startActivity(intent);
//            }
//
//            return true;
//        }
//    }
//
//    private static boolean isIntentAvailable(Intent intent) {
//        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
//    }
//
//    private static boolean startActivityForResult(AppCompatActivity activity, Bundle extras, String pkg, String cls, int requestCode, Bundle options) {
//        Intent intent = new Intent("android.intent.action.VIEW");
//        if (extras != null) {
//            intent.putExtras(extras);
//        }
//
//        intent.setComponent(new ComponentName(pkg, cls));
//        return startActivityForResult(intent, activity, requestCode, options);
//    }
//
//    private static boolean startActivityForResult(Intent intent, AppCompatActivity activity, int requestCode, Bundle options) {
//        if (!isIntentAvailable(intent)) {
//            Log.e("ActivityUtils", "intent is unavailable");
//            return false;
//        } else {
//            if (options != null && VERSION.SDK_INT >= 16) {
//                activity.startActivityForResult(intent, requestCode, options);
//            } else {
//                activity.startActivityForResult(intent, requestCode);
//            }
//
//            return true;
//        }
//    }
//
//    private static void startActivities(Intent[] intents, Context context, Bundle options) {
//        if (!(context instanceof AppCompatActivity)) {
//            Intent[] var3 = intents;
//            int var4 = intents.length;
//
//            for(int var5 = 0; var5 < var4; ++var5) {
//                Intent intent = var3[var5];
//                intent.addFlags(268435456);
//            }
//        }
//
//        if (options != null && VERSION.SDK_INT >= 16) {
//            context.startActivities(intents, options);
//        } else {
//            context.startActivities(intents);
//        }
//
//    }
//
//    private static Bundle getOptionsBundle(Context context, int enterAnim, int exitAnim) {
//        return ActivityOptionsCompat.makeCustomAnimation(context, enterAnim, exitAnim).toBundle();
//    }
//
//    private static Bundle getOptionsBundle(AppCompatActivity activity, View[] sharedElements) {
//        if (VERSION.SDK_INT < 21) {
//            return null;
//        } else if (sharedElements == null) {
//            return null;
//        } else {
//            int len = sharedElements.length;
//            if (len <= 0) {
//                return null;
//            } else {
//                Pair<View, String>[] pairs = new Pair[len];
//
//                for(int i = 0; i < len; ++i) {
//                    pairs[i] = Pair.create(sharedElements[i], sharedElements[i].getTransitionName());
//                }
//
//                return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs).toBundle();
//            }
//        }
//    }
//}
