package org.d3ifcool4089.praassesment


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import org.d3ifcool4089.praassesment.databinding.FragmentSegitigaSBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaSS : Fragment() {

    private var luas = 0.0
   private var kell = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSegitigaSBinding>(inflater, R.layout.fragment_segitiga_s, container, false)

        if (savedInstanceState != null) {
            luas =savedInstanceState.getDouble("luas")
            kell = savedInstanceState.getString("kell", kell)

            binding.tvHasilLuas.text = luas.toString()
            binding.tvHasilKell.text = kell
        }

        binding.btnHitung.setOnClickListener {
            var alas = binding.etAlas.text.toString().toDouble()
            var tinggi = binding.etTinggi.text.toString().toDouble()

            var sisiMiring = Math.sqrt(Math.pow(alas, 2.0) + Math.pow(tinggi, 2.0))

            luas = (alas*tinggi)/2
            val hitungKell = alas + tinggi + sisiMiring

            kell = String.format("%.2f", hitungKell)
            binding.tvHasilLuas.text = luas.toString()
            binding.tvHasilKell.text = kell
        }

        binding.btnShare.setOnClickListener {
            onShare()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luas", luas)
        outState.putString("kell", kell)
        super.onSaveInstanceState(outState)
    }

    private fun onShare() {
        val shareIntent = this.activity?.let {
            ShareCompat.IntentBuilder.from(it)
                .setText("Luas Persegi Panjang = " + luas + "\nKeliling Persegi Panjang = " + kell)
                .setType("text/plain")
                .intent
        }

        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this.activity, "Sharing Not Available", Toast.LENGTH_LONG).show()
        }
    }


}
