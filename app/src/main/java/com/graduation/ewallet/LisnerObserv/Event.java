package com.graduation.ewallet.LisnerObserv;


/*
 * created by Mahmoud shehatah at 6/22/2019
 * */
public class Event {

    private EventBalance mOnEventListener;

    public void setOnEventListener(EventBalance listener) {
        mOnEventListener = listener;
    }

    public void doEvent(String newBalance) {
        /*
         * code code code
         */

        // and in the end



        if (mOnEventListener != null)
            mOnEventListener.eventBalance(newBalance); // event result object :)
    }
}
