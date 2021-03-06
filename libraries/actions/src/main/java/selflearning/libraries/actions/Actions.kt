package selflearning.libraries.actions

import android.content.Context
import android.content.Intent

object Actions {

    fun openFirstIntent(context: Context) = internalIntent(context, "com.selflearning.chemistree.f_mendeleev_table.table.MendeleevTable")

    fun openSharingIntent(context: Context) = internalIntent(context,"com.jeroenmols.modularization.sharing.open")

    private fun internalIntent(context: Context, action: String) = Intent().setClassName(context, action)
//        Intent(action).setPackage(context.packageName)
}