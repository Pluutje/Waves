package app.aaps.core.interfaces.insulin

import app.aaps.core.data.iob.Iob
import app.aaps.core.data.model.BS
import app.aaps.core.data.model.ICfg
import app.aaps.core.interfaces.configuration.ConfigExportImport

interface Insulin : ConfigExportImport {

    enum class InsulinType(val value: Int) {
        UNKNOWN(-1),

        // int FAST_ACTING_INSULIN = 0; // old model no longer available
        // int FAST_ACTING_INSULIN_PROLONGED = 1; // old model no longer available
        OREF_RAPID_ACTING(2),
        OREF_ULTRA_RAPID_ACTING(3),
        OREF_FREE_PEAK(4),
        OREF_LYUMJEV(5),
        CUSTOM_PD(104),
        OREF_LYUMJEV_U100_PD(105),
        OREF_LYUMJEV_U200_PD(205);

        companion object {

            private val map = entries.associateBy(InsulinType::value)
            fun fromInt(type: Int) = map[type]
        }
    }

    val id: InsulinType
    val friendlyName: String
    val comment: String
    val dia: Double
    val peak: Int
    val isPD: Boolean

    fun iobCalcForTreatment(bolus: BS, time: Long, dia: Double): Iob
    fun iobCalcPeakForTreatment(bolus: BS, dia: Double): Iob

    val iCfg: ICfg
}