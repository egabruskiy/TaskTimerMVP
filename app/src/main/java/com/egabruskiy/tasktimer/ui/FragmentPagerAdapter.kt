//package com.egabruskiy.tasktimer.ui
//
//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentManager
//import android.support.v4.app.FragmentStatePagerAdapter
//
//class FragmentPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
//
//
//
//    override fun getItem(position: Int): Fragment {
//        return when (position) {
//            0 -> {
//             DayListFragment.newInstance() }
//
//            1 -> {
//                StatisticsFragment.newInstance()
//            }
//
//            else -> DayListFragment.newInstance()
//        }
//    }
//
//    override fun getItemPosition(`object`: Any): Int {
//
////        StatisticsFragment.newInstance().update()
//
//        if (`object` is StatisticsFragment) {
//        }
//        return super.getItemPosition(`object`)
//    }
//
//
////    fun getFragment(position: Int): Fragment? {
////        var fragment: Fragment? = null
////        val tag = mFragmentTags.get(position)
////        if (tag != null) {
////            fragment = mFragmentManager.findFragmentByTag(tag)
////        }
////        return fragment
////    }
//
//
//    override fun getCount() = 2
//}