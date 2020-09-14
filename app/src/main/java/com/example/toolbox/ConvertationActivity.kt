package com.example.toolbox

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_convertation.*

@Suppress("UNUSED_PARAMETER")
class ConvertationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convertation)

        val typeArr =
            arrayOf("دما", "طول", "زمان")
        var fromArr: Array<String> = arrayOf()
        var type = 0
        var from = 0
        var to = 0
        convertation_type_spinner.adapter = ArrayAdapter(this, R.layout.spinner, typeArr)
        convertation_type_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            fromArr = arrayOf("سلسیوس", "فارنهایت", "کلوین")
                        }
                        1 -> {
                            fromArr = arrayOf("متر", "مایل", "یارد", "اینچ", "فوت")
                        }
                        2 -> {
                            fromArr = arrayOf("ثانیه", "دقیقه", "ساعت", "روز")
                        }
                    }
                    type = position
                    convertation_change_spinner_from.adapter =
                        ArrayAdapter(this@ConvertationActivity, R.layout.spinner, fromArr)
                    convertation_change_spinner_to.adapter =
                        ArrayAdapter(this@ConvertationActivity, R.layout.spinner, fromArr)
                    val txt = convertation_number.text.toString().trim()
                    if (txt == "") convertation_result_text.text =
                        getString(R.string.emptyConvertationText)
                    else {
                        convertation_result_text.text =
                            convert(type, from, to, fromArr, txt.toDouble())
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

        convertation_change_spinner_from.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    from = position
                    val txt = convertation_number.text.toString().trim()
                    if (txt == "") convertation_result_text.text =
                        getString(R.string.emptyConvertationText)
                    else {
                        convertation_result_text.text =
                            convert(type, from, to, fromArr, txt.toDouble())
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

        convertation_change_spinner_to.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    to = position
                    val txt = convertation_number.text.toString().trim()
                    if (txt == "") convertation_result_text.text =
                        getString(R.string.emptyConvertationText)
                    else {
                        convertation_result_text.text =
                            convert(type, from, to, fromArr, txt.toDouble())
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }


        convertation_number.addTextChangedListener(
            object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                @SuppressLint("SetTextI18n")
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    val txt = convertation_number.text.toString().trim()
                    if (txt == "") convertation_result_text.text =
                        getString(R.string.emptyConvertationText)
                    else {
                        convertation_result_text.text =
                            convert(type, from, to, fromArr, txt.toDouble())
                    }
                }
            })

    }

    private fun convert(
        type: Int,
        from: Int,
        to: Int,
        fromArr: Array<String>,
        number: Double
    ): CharSequence? {
        println("$from \t $to")
        if (from == to) {

            return "هر  " + number + "  " + fromArr[from] + "  برابر است با  " + number + "  " + fromArr[to]
        }
        var result = 0.0
        when (type) {
            0 -> {
                result = convertTemperature(from, to, number);
            }
            1 -> {
                result = convertDistance(from, to, number);
            }
            2 -> {
                result = convertTime(from, to, number);
            }
        }
        return "هر  " + number + "  " + fromArr[from] + "  برابر است با  " + result + "  " + fromArr[to]
    }

    private fun convertTime(from: Int, to: Int, number: Double): Double {
        var result = 0.0
        when (from) {
            0 -> {
                when (to) {
                    1 -> {
                        result = number / 60
                    }
                    2 -> {
                        result = number * 0.0002777777777777778
                    }
                    3 -> {
                        result = number * 0.000011574074074074073
                    }
                }
            }
            1 -> {
                when (to) {
                    0 -> {
                        result = number * 60
                    }
                    2 -> {
                        result = number / 60
                    }
                    3 -> {
                        result = number / (60 * 24)
                    }
                }
            }
            2 -> {
                when (to) {
                    0 -> {
                        result = number * 60 * 60
                    }
                    1 -> {
                        result = number * 60
                    }
                    3 -> {
                        result = number / 24
                    }
                }
            }
            3 -> {
                when (to) {
                    0 -> {
                        result = number * 60 * 60 * 24
                    }
                    1 -> {
                        result = number * 60 * 24
                    }
                    2 -> {
                        result = number * 24
                    }
                }
            }
        }
        return result
    }

    private fun convertDistance(from: Int, to: Int, number: Double): Double {
        var result = 0.0
        when (from) {
            0 -> {
                when (to) {
                    1 -> {
                        result = number * 0.0006213712
                    }
                    2 -> {
                        result = number * 1.0936132983
                    }
                    3 -> {
                        result = number * 39.37007874
                    }
                    4 -> {
                        result = number * 3.280839895
                    }
                }
            }
            1 -> {
                when (to) {
                    0 -> {
                        result = number * 1609.3439798947877
                    }
                    2 -> {
                        result = number * 1759.9999779519876
                    }
                    3 -> {
                        result = number * 63359.99920820276
                    }
                    4 -> {
                        result = number * 5279.999934016897
                    }
                }
            }
            2 -> {
                when (to) {
                    0 -> {
                        result = number * 0.9144000000315285
                    }
                    1 -> {
                        result = number * 0.0005681818252995909
                    }
                    3 -> {
                        result = number * 36.00000000109728
                    }
                    4 -> {
                        result = number * 3.00000000009144
                    }
                }
            }
            3 -> {
                when (to) {
                    0 -> {
                        result = number * 0.0254000000001016
                    }
                    1 -> {
                        result = number * 0.000015782828480063133
                    }
                    2 -> {
                        result = number * 0.027777777776931113
                    }
                    4 -> {
                        result = number * 0.08333333333333334
                    }
                }
            }
            4 -> {
                when (to) {
                    0 -> {
                        result = number * 0.3048000000012192
                    }
                    1 -> {
                        result = number * 0.00018939394176075756
                    }
                    2 -> {
                        result = number * 0.33333333332317333
                    }
                    3 -> {
                        result = number * 11.999999999999998
                    }
                }
            }
        }
        return result
    }

    private fun convertTemperature(from: Int, to: Int, number: Double): Double {
        var result = 0.0
        when (from) {
            0 -> {
                if (to == 1) {
                    result = 32 + (number * 1.8)
                } else if (to == 2) {
                    result = 273.15 + number
                }
            }
            1 -> {
                if (to == 0) {
                    result = (number - 32) / 1.8
                } else if (to == 2) {
                    result = (number + 459.67) / 1.8
                }
            }
            2 -> {
                if (to == 0) {
                    result = number - 273.15
                } else if (to == 1) {
                    result = (number * 1.8) - 459.67
                }
            }
        }
        return result
    }
}
