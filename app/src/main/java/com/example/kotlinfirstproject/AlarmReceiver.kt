

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.kotlinfirstproject.Utilll

class AlarmReceiver : BroadcastReceiver()
{
    override fun onReceive(p0: Context?, p1: Intent?) {

        Log.d("Onreceive","Ser")
        var schedule = Utilll()
        if (p0 != null) {
            schedule.scheduleJob(p0)
        };
//        val background = Intent(p0, MyService::class.java)
//        p0?.startService(background)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

     }

}