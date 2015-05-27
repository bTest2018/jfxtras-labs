/**
 * AgendaTrial1.java
 *
 * Copyright (c) 2011-2014, JFXtras
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the organization nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jfxtras.labs.scene.control.gauge.linear.trial;

import java.util.List;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import jfxtras.labs.scene.control.gauge.linear.LinearGauge;
import jfxtras.labs.scene.control.gauge.linear.SimpleMetroArcGauge;
import jfxtras.labs.scene.control.gauge.linear.elements.CompleteSegment;
import jfxtras.labs.scene.control.gauge.linear.elements.Indicator;
import jfxtras.labs.scene.control.gauge.linear.elements.PercentSegment;
import jfxtras.labs.scene.control.gauge.linear.elements.Segment;

/**
 * @author Tom Eugelink
 */
public class SimpleMetroArcGaugeTrial1 extends AbstractLinearGaugeTrial1 {
	
    public static void main(String[] args) {
        launch(args);       
    }

	@Override
	public LinearGauge<?> createLinearGauge() {
		return new SimpleMetroArcGauge();
	}
	
	@Override
	public void addDeviatingGauges(List<LinearGauge<?>> gauges, FlowPane lFlowPane) {
        
		// 10 segments, color schema
		{
			final LinearGauge<?> lLinearGauge = createLinearGauge();
			lLinearGauge.setStyle("-fx-border-color: #000000;");
			lLinearGauge.getStyleClass().add("colorscheme-green-to-red-10");
			for (int i = 0; i < 10; i++) {
				Segment lSegment = new PercentSegment(lLinearGauge, i * 10.0, (i+1) * 10.0);
				lLinearGauge.segments().add(lSegment);
			}
			lFlowPane.getChildren().add(lLinearGauge);
			gauges.add(lLinearGauge);
		}
        
        // 20 segments
		{
			HBox lHBox = new HBox();
			final LinearGauge<?> lLinearGauge = createLinearGauge();
			lLinearGauge.setStyle("-fx-border-color: #000000;");
			lLinearGauge.getStyleClass().add("colorscheme-green-to-red-10");
			for (int i = 0; i < 20; i++) {
				Segment lSegment = new PercentSegment(lLinearGauge, i * 5.0, (i+1) * 5.0);
				lLinearGauge.segments().add(lSegment);
			}
			lHBox.getChildren().add(lLinearGauge);

			lFlowPane.getChildren().add(lHBox);
			
			gauges.add(lLinearGauge);
		}
        
		// manually show indicators
		{
			final LinearGauge<?> lLinearGauge = createLinearGauge();
			lLinearGauge.indicators().add(new Indicator(0, "warning"));
			lLinearGauge.indicators().add(new Indicator(1, "error"));
			lLinearGauge.setStyle("-fx-border-color: #000000; -fxx-warning-indicator-visibility: visible; -fxx-error-indicator-visibility: visible; ");
			lFlowPane.getChildren().add(lLinearGauge);
			gauges.add(lLinearGauge);
		}
        
		// 10 segments, transparent, with segment related indicators
		{
			final LinearGauge<?> lLinearGauge = createLinearGauge();
			lLinearGauge.indicators().add(new Indicator(0, "warning"));
			lLinearGauge.indicators().add(new Indicator(1, "error"));
			lLinearGauge.setId("segmentRelatedIndicators");
			lLinearGauge.setStyle("-fx-border-color: #000000;");
			lLinearGauge.getStyleClass().add("colorscheme-first-grey-rest-transparent-10");
			lLinearGauge.segments().add(new CompleteSegment(lLinearGauge));
			lLinearGauge.segments().add(new PercentSegment(lLinearGauge, 50.0, 100.0, "warningSegment"));
			lLinearGauge.segments().add(new PercentSegment(lLinearGauge, 75.0, 100.0, "errorSegment"));
			lFlowPane.getChildren().add(lLinearGauge);
			gauges.add(lLinearGauge);
		}
	}

}

