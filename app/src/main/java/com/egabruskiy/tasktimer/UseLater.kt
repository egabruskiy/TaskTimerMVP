import android.os.Bundle

//package com.egabruskiy.tasktimer
//
//import com.egabruskiy.tasktimer.util.PreciseCountdown
//import kotlinx.android.synthetic.main.activity_task.*
//
//private fun startTimer() {
//
//    if (timerRunning) { return }
//    timerRunning = true
//    timer =  object: PreciseCountdown(currentTime, 1000) {
//        override fun onTick(millisUntilFinished:Long) {
//            currentTime = millisUntilFinished
//
//            runThread()
////                task_time_in_day.setText(msToHHMMSS(currentTime))
////                progress_bar_task.progress = currentTime.toInt()
////                progress_bar_task.progressDrawable
//// note that this runs on a different thread, so to update any GUI components you need to use Activity.runOnUiThread()
//        }
//
//        override fun onFinished() {
//            onTick(0)
//            // when the timer finishes onTick isn't called
//            // count down is finished
//        }
//    }
//    timer?.start()
//
////        to start the countdown, simply call countDown.start(). countDown.stop() stops the countDown, which could be restarted using countDown.restart().
//}
//
//private fun runThread() {
//
//    runOnUiThread(
//            object : Runnable {
//                override fun run() {
//                    task_time_in_day.setText(msToHHMMSS(currentTime))
//                    progress_bar_task.progress = currentTime.toInt()
//                    progress_bar_task.progressDrawable
//                }
//            }
//    )
//
////        object : Thread() {
////            override fun run() {
////                while (timerRunning) {
////                    try {
////                       runOnUiThread {
////                            task_time_in_day.setText(msToHHMMSS(currentTime))
////                            progress_bar_task.progress = currentTime.toInt()
////                            progress_bar_task.progressDrawable
////                       }
////                    } catch (e: InterruptedException) { e.printStackTrace() }
////                }
////            }
////        }.start()
//}







//    private fun loadImageUsingGlide() {
//        secondFragmentProgressBar.visibility = View.VISIBLE
//        GlideApp.with(activity).asBitmap()
//                .load(Uri.parse(imageUrl))
//                .into(object : BitmapImageViewTarget(secondFragmentImageView){
//                    override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
//                        super.onResourceReady(resource, transition)
//                        secondFragmentProgressBar.visibility = View.INVISIBLE
//                    }
//                })
//    }



//
//private val imageUrl = "https://images.pexels.com/photos/163065/mobile-phone-android-apps-phone-163065.jpeg"
//
//
//override fun onActivityCreated(savedInstanceState: Bundle?) {
//    super.onActivityCreated(savedInstanceState)
////        loadImageUsingGlide()
//}