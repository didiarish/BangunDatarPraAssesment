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
import org.d3ifcool4089.praassesment.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjang : Fragment() {

    private var luas = 0
    private var kell = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(
            inflater,
            R.layout.fragment_persegi_panjang,
            container,
            false
        )

        if (savedInstanceState != null) {
            luas = savedInstanceState.getInt("luas")
            kell = savedInstanceState.getInt("kell")

            binding.tvHasilLuas.text = luas.toString()
            binding.tvHasilKell.text = kell.toString()
        }

        binding.btnHitung.setOnClickListener {
            var panjang = binding.etPanjang.text.toString().toInt()
            var lebar = binding.etLebar.text.toString().toInt()

            luas = panjang * lebar
            kell = 2 * (panjang + lebar)

            binding.tvHasilLuas.text = luas.toString()
            binding.tvHasilKell.text = kell.toString()
        }

        binding.btnShare.setOnClickListener {
            onShare()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("luas", luas)
        outState.putInt("kell", kell)
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
