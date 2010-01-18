/*
 * Copyright (c) 2007 BUSINESS OBJECTS SOFTWARE LIMITED
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *  
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *  
 *     * Neither the name of Business Objects nor the names of its contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *  
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */


/*
 * ChooseActionCard.java
 * Created: 2-Apr-2004
 * By: Rick Cameron
 */

package org.openquark.samples.bam.ui;

import java.util.Collection;

import org.openquark.cal.services.GemEntity;
import org.openquark.samples.bam.MonitorApp;
import org.openquark.samples.bam.model.MonitorJobDescription;



/**
 * 
 * 
 */
public class ChooseActionCard extends ChooseGemCard {

    private static final long serialVersionUID = -159532149198311428L;
    static final String CARD_NAME = "ChooseAction"; 
    
    /**
     * Constructor ChooseActionCard
     * 
     * @param app
     * @param jobDescription
     */
    public ChooseActionCard (MonitorApp app, MonitorJobDescription jobDescription, ConfigureGemWizardState wizardState) {
        super (app, wizardState);
    }

    /**
     * @see org.openquark.util.ui.WizardCard#getTitle()
     */
    @Override
    protected String getTitle () {
        return "Choose Action";
    }

    /**
     * @see org.openquark.util.ui.WizardCard#getSubtitle()
     */
    @Override
    protected String getSubtitle () {
        return "Choose the gem to use for the action";
    }

    /**
     * @see org.openquark.util.ui.WizardCard#getCardName()
     */
    @Override
    public String getCardName () {
        return CARD_NAME;
    }

    /**
     * @see org.openquark.util.ui.WizardCard#getNextCardName()
     */
    @Override
    protected String getNextCardName () {
        return BindInputsCard.CARD_NAME;
    }

    /**
     * @see org.openquark.util.ui.WizardCard#canFinish()
     */
    @Override
    protected boolean canFinish () {
        return false;
    }

    /**
     * @see org.openquark.util.ui.WizardCard#onFinish()
     */
    @Override
    protected boolean onFinish () {
        return false;
    }

    /**
     * @see org.openquark.samples.bam.ui.ChooseGemCard#getAvailableGems()
     */
    @Override
    protected Collection<GemEntity> getAvailableGems () {
        return getApp ().getActionGemManager().getAvailableGems();
    }

    /**
     * @see org.openquark.util.ui.WizardCard#getTipInfo()
     */
    @Override
    protected TipInfo getTipInfo () {
        return new TipInfo (INFO_TIP, "Press the Next button to select inputs for the gem.");
    }

}