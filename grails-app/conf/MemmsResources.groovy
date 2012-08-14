/**
 * Copyright (c) 2011, Clinton Health Access Initiative.
*
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the <organization> nor the
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

/**
 * @author Jean Kahigiso M.
 *
 */
modules = {
	
	// overrides, let's put jquery in the core bundle
	overrides {
		jquery {
			defaultBundle 'core'
		}
	}
	
	// modules
	core {
		dependsOn 'jquery'
		
		resource url: '/css/screen.css', bundle: 'core'
	}
	error {
		resource url: '/css/errors.css'
	}
	chosen {
		dependsOn 'jquery'

		resource url: '/js/jquery/chosen/chosen.jquery.js', bundle: 'core'
		resource url: '/js/jquery/chosen/ajax-chosen.js', bundle: 'core'
		resource url: '/js/jquery/chosen/chosen.css', bundle: 'core'
	}
	
	datepicker {
		dependsOn 'jquery'

		resource url: '/js/jquery/datepicker/glDatePicker.js', bundle: 'core'
		resource url: '/js/jquery/datepicker/datepicker.css', bundle: 'core'
	}
	
	popup {
		dependsOn 'jquery'

		resource url: '/js/jquery/popup/popup.js', bundle: 'core'
		resource url: '/js/jquery/popup/popup.css', bundle: 'core'
	}

}