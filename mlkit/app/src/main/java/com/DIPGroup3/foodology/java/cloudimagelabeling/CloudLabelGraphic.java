// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.DIPGroup3.foodology.java.cloudimagelabeling;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.DIPGroup3.foodology.common.GraphicOverlay;
import com.DIPGroup3.foodology.common.GraphicOverlay.Graphic;

import java.util.List;

/** Graphic instance for rendering detected label. */
public class CloudLabelGraphic extends Graphic {
  private  Paint textPaint;
  private  Paint textPaint2;

  private final GraphicOverlay overlay;

  private List<String> labels;
  private List<String> second_lable;

  CloudLabelGraphic(GraphicOverlay overlay, List<String> labels, List<String> seconds) {
    super(overlay);
    this.overlay = overlay;
    this.labels = labels;
    this.second_lable = seconds;
    textPaint = new Paint();
    textPaint2 = new Paint();
    textPaint.setColor(Color.RED);
    textPaint.setTextSize(60.0f);
    textPaint2.setColor(Color.BLUE);
    textPaint2.setTextSize(120.0f);


  }


  //draw label at certain position
  //edit according to future needs.
  @Override
  public synchronized void draw(Canvas canvas) {
    float x = overlay.getWidth() / 4.0f;
    float y = overlay.getHeight() / 4.0f;

    for (String label : labels) {
      canvas.drawText(label, x, y, textPaint);
      y = y - 62.0f;
    }
    //label is defined as String
    for (String Second : second_lable) {
      canvas.drawText(Second, 500, 200, textPaint2);

    }
  }
}
