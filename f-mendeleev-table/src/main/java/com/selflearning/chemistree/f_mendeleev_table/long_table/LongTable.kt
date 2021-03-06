package com.selflearning.chemistree.f_mendeleev_table.long_table
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.selflearning.chemistree.f_mendeleev_table.databinding.LongTableFragmentBinding
import com.selflearning.chemistree.f_mendeleev_table.databinding.MainLongPeriodicTableBinding
import java.util.*

class LongTable : Fragment() {
    private var mViewModel: LongTableViewModel? = null
    private val toast: Toast? = null
    var dialog: Dialog? = null

    private var _binding: LongTableFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var includeBinding: MainLongPeriodicTableBinding

    private val linearLayoutList: MutableList<LinearLayout?> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = LongTableFragmentBinding.inflate(inflater, container, false)
        includeBinding = MainLongPeriodicTableBinding.bind(binding.root)

//        linearLayoutList.add(includeBinding.element1)
//        linearLayoutList.add(includeBinding.element2)
        linearLayoutList.add(includeBinding.element3)
        linearLayoutList.add(includeBinding.element4)
        linearLayoutList.add(includeBinding.element5)
        linearLayoutList.add(includeBinding.element6)
        linearLayoutList.add(includeBinding.element7)
        linearLayoutList.add(includeBinding.element8)
        linearLayoutList.add(includeBinding.element9)
        linearLayoutList.add(includeBinding.element10)
        linearLayoutList.add(includeBinding.element11)
        linearLayoutList.add(includeBinding.element12)
        linearLayoutList.add(includeBinding.element13)
        linearLayoutList.add(includeBinding.element14)
        linearLayoutList.add(includeBinding.element15)
        linearLayoutList.add(includeBinding.element16)
        linearLayoutList.add(includeBinding.element17)
        linearLayoutList.add(includeBinding.element18)
        linearLayoutList.add(includeBinding.element19)
        linearLayoutList.add(includeBinding.element20)
        linearLayoutList.add(includeBinding.element21)
        linearLayoutList.add(includeBinding.element22)
        linearLayoutList.add(includeBinding.element23)
        linearLayoutList.add(includeBinding.element24)
        linearLayoutList.add(includeBinding.element25)
        linearLayoutList.add(includeBinding.element26)
        linearLayoutList.add(includeBinding.element27)
        linearLayoutList.add(includeBinding.element28)
        linearLayoutList.add(includeBinding.element29)
        linearLayoutList.add(includeBinding.element30)
        linearLayoutList.add(includeBinding.element31)
        linearLayoutList.add(includeBinding.element32)
        linearLayoutList.add(includeBinding.element33)
        linearLayoutList.add(includeBinding.element34)
        linearLayoutList.add(includeBinding.element35)
        linearLayoutList.add(includeBinding.element36)
        linearLayoutList.add(includeBinding.element37)
        linearLayoutList.add(includeBinding.element38)
        linearLayoutList.add(includeBinding.element39)
        linearLayoutList.add(includeBinding.element40)
        linearLayoutList.add(includeBinding.element41)
        linearLayoutList.add(includeBinding.element42)
        linearLayoutList.add(includeBinding.element43)
        linearLayoutList.add(includeBinding.element44)
        linearLayoutList.add(includeBinding.element45)
        linearLayoutList.add(includeBinding.element46)
        linearLayoutList.add(includeBinding.element47)
        linearLayoutList.add(includeBinding.element48)
        linearLayoutList.add(includeBinding.element49)
        linearLayoutList.add(includeBinding.element50)
        linearLayoutList.add(includeBinding.element51)
        linearLayoutList.add(includeBinding.element52)
        linearLayoutList.add(includeBinding.element53)
        linearLayoutList.add(includeBinding.element54)
        linearLayoutList.add(includeBinding.element55)
        linearLayoutList.add(includeBinding.element56)
        linearLayoutList.add(includeBinding.element57)
        linearLayoutList.add(includeBinding.element58)
        linearLayoutList.add(includeBinding.element59)
        linearLayoutList.add(includeBinding.element60)
        linearLayoutList.add(includeBinding.element61)
        linearLayoutList.add(includeBinding.element62)
        linearLayoutList.add(includeBinding.element63)
        linearLayoutList.add(includeBinding.element64)
        linearLayoutList.add(includeBinding.element65)
        linearLayoutList.add(includeBinding.element66)
        linearLayoutList.add(includeBinding.element67)
        linearLayoutList.add(includeBinding.element68)
        linearLayoutList.add(includeBinding.element69)
        linearLayoutList.add(includeBinding.element70)
        linearLayoutList.add(includeBinding.element71)
        linearLayoutList.add(includeBinding.element72)
        linearLayoutList.add(includeBinding.element73)
        linearLayoutList.add(includeBinding.element74)
        linearLayoutList.add(includeBinding.element75)
        linearLayoutList.add(includeBinding.element76)
        linearLayoutList.add(includeBinding.element77)
        linearLayoutList.add(includeBinding.element78)
        linearLayoutList.add(includeBinding.element79)
        linearLayoutList.add(includeBinding.element80)
        linearLayoutList.add(includeBinding.element81)
        linearLayoutList.add(includeBinding.element82)
        linearLayoutList.add(includeBinding.element83)
        linearLayoutList.add(includeBinding.element84)
        linearLayoutList.add(includeBinding.element85)
        linearLayoutList.add(includeBinding.element86)
        linearLayoutList.add(includeBinding.element87)
        linearLayoutList.add(includeBinding.element88)
        linearLayoutList.add(includeBinding.element89)
        linearLayoutList.add(includeBinding.element90)
        linearLayoutList.add(includeBinding.element91)
        linearLayoutList.add(includeBinding.element92)
        linearLayoutList.add(includeBinding.element93)
        linearLayoutList.add(includeBinding.element94)
        linearLayoutList.add(includeBinding.element95)
        linearLayoutList.add(includeBinding.element96)
        linearLayoutList.add(includeBinding.element97)
        linearLayoutList.add(includeBinding.element98)
        linearLayoutList.add(includeBinding.element99)
        linearLayoutList.add(includeBinding.element100)
        linearLayoutList.add(includeBinding.element101)
        linearLayoutList.add(includeBinding.element102)
        linearLayoutList.add(includeBinding.element103)
        linearLayoutList.add(includeBinding.element104)
        linearLayoutList.add(includeBinding.element105)
        linearLayoutList.add(includeBinding.element106)
        linearLayoutList.add(includeBinding.element107)
        linearLayoutList.add(includeBinding.element108)
        linearLayoutList.add(includeBinding.element109)
        linearLayoutList.add(includeBinding.element110)
        linearLayoutList.add(includeBinding.element111)
        linearLayoutList.add(includeBinding.element112)
        linearLayoutList.add(includeBinding.element113)
        linearLayoutList.add(includeBinding.element114)
        linearLayoutList.add(includeBinding.element115)
        linearLayoutList.add(includeBinding.element116)
        linearLayoutList.add(includeBinding.element117)
        linearLayoutList.add(includeBinding.element118)
        val onClickListener = View.OnClickListener { v ->
            toast?.cancel()
            val tag = v.tag.toString()
            val num = tag.toInt()
//            val intent = Intent(activity, ElementInfoActivity::class.java)
//            intent.putExtra("elementNum", num)
//            startActivity(intent)

//                Element element = DatabaseAccess.getInstance(getContext()).getElementFromTable(tag);
//                toast = Toast.makeText(getContext(), "element " + element.getRuTitle() + "\nweight " + element.getWeight(), Toast.LENGTH_SHORT);
//                toast.show();
        }

        includeBinding.element1.setOnClickListener {v ->
            toast?.cancel()
            val tag = v.tag.toString()
            val num = tag.toInt()
//            val intent = Intent(activity, ElementInfoActivity::class.java)
//            intent.putExtra("elementNum", num)
//            startActivity(intent)
        }
        for (layout in linearLayoutList) {
            layout!!.setOnClickListener(onClickListener)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(LongTableViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        @JvmStatic
        fun newInstance(): LongTable {
            return LongTable()
        }
    }
}