package com.slas.healthendar.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.slas.healthendar.entity.Visit
import com.slas.healthendar.ui.elements.ExpandedItem
import com.slas.healthendar.ui.elements.TimeLabel
import com.slas.healthendar.ui.icon.ClockIcon
import com.slas.healthendar.ui.theme.Typography

@Composable
fun VisitFragment(visit: Visit, dialog: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        DoctorLabel(visit)
        IconLabel(Icons.Filled.LocationOn, visit.localization)
        IconLabel(ClockIcon(), TimeLabel(visit.time))
        IconLabel(Icons.Filled.Phone, if (visit.phone != null) visit.phone!! else "N/A")
        IconLabel(Icons.Filled.MailOutline, if (visit.mail != null) visit.mail!! else "N/A")
        Divider(modifier = Modifier.fillMaxWidth())
        LazyColumn {
            items(visit.reminders) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                ) {
                    ExpandedItem(it)
                }

            }
        }

        dialog()

    }
}

@Composable
fun IconLabel(icon: ImageVector, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "icon",
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = label,
            fontWeight = Typography.labelLarge.fontWeight,
            fontSize = Typography.labelLarge.fontSize,
            fontFamily = Typography.labelLarge.fontFamily
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun DoctorLabel(visit: Visit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .clip(shape = CircleShape)
                .width(40.dp)
                .height(40.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${visit.doctor[0]}",
                fontWeight = Typography.labelLarge.fontWeight,
                fontSize = Typography.labelLarge.fontSize,
                fontFamily = Typography.labelLarge.fontFamily,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )

        }
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = "${visit.specialization}, ${visit.doctor}",
            fontWeight = Typography.labelLarge.fontWeight,
            fontSize = Typography.labelLarge.fontSize,
            fontFamily = Typography.labelLarge.fontFamily
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}
