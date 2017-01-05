// This file was automatically generated from select.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace ddm.
 * @hassoydeltemplate {ddm.field}
 * @public
 */

if (typeof ddm == 'undefined') { var ddm = {}; }


ddm.__deltemplate_s2_2dbfb377 = function(opt_data, opt_ignored) {
  return '' + ddm.select(opt_data);
};
if (goog.DEBUG) {
  ddm.__deltemplate_s2_2dbfb377.soyTemplateName = 'ddm.__deltemplate_s2_2dbfb377';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('ddm.field'), 'select', 0, ddm.__deltemplate_s2_2dbfb377);


ddm.select = function(opt_data, opt_ignored) {
  var output = '<div class="form-group' + soy.$$escapeHtmlAttribute(opt_data.visible ? '' : ' hide') + '" data-fieldname="' + soy.$$escapeHtmlAttribute(opt_data.name) + '"><div class="input-select-wrapper">' + ((opt_data.showLabel) ? ddm.select_label(opt_data) : '') + '<div class="form-builder-select-field input-group-container">' + ((! opt_data.readOnly) ? ddm.hidden_select(soy.$$augmentMap(opt_data, {options: opt_data.options, name: opt_data.name, multiple: opt_data.multiple, dir: opt_data.dir, value: opt_data.value, string: opt_data.strings})) : '');
  if (! opt_data.multiple) {
    output += '<a class="form-control select-field-trigger" ' + ((opt_data.readOnly) ? 'disabled' : '') + ' dir="' + soy.$$escapeHtmlAttribute(opt_data.dir) + '" href="javascript:;" id="' + soy.$$escapeHtmlAttribute(opt_data.name) + '" name="' + soy.$$escapeHtmlAttribute(opt_data.name) + '">';
    var selectedLabel__soy40 = '' + ((opt_data.value && opt_data.value.value) ? soy.$$escapeHtml(opt_data.value.label) : soy.$$escapeHtml(opt_data.strings.chooseAnOption));
    output += '<span class="option-selected">' + soy.$$escapeHtml(selectedLabel__soy40) + '</span></a>';
  } else {
  }
  output += ((! opt_data.readOnly) ? '<div class="drop-chosen hide"><div class="search-chosen"><div class="select-search-container">' + ((opt_data.selectSearchIcon) ? '<a class="" href="javascript:;">' + soy.$$filterNoAutoescape(opt_data.selectSearchIcon) + '</a>' : '') + '</div><input placeholder="Search" class="drop-chosen-search" type="text" autocomplete="off"></div><ul class="results-chosen">' + ddm.select_options(opt_data) + '</ul></div>' : '') + ((opt_data.selectCaretDoubleIcon) ? '<a class="select-arrow-down-container" href="javascript:;">' + soy.$$filterNoAutoescape(opt_data.selectCaretDoubleIcon) + '</a>' : '') + '</div>' + ((opt_data.childElementsHTML) ? soy.$$filterNoAutoescape(opt_data.childElementsHTML) : '') + '</div></div>';
  return output;
};
if (goog.DEBUG) {
  ddm.select.soyTemplateName = 'ddm.select';
}


ddm.select_label = function(opt_data, opt_ignored) {
  return '<label class="control-label" for="' + soy.$$escapeHtmlAttribute(opt_data.name) + '">' + soy.$$escapeHtml(opt_data.label) + ((opt_data.required) ? '<span class="icon-asterisk text-warning"></span>' : '') + '</label>' + ((opt_data.tip) ? '<p class="liferay-ddm-form-field-tip">' + soy.$$escapeHtml(opt_data.tip) + '</p>' : '');
};
if (goog.DEBUG) {
  ddm.select_label.soyTemplateName = 'ddm.select_label';
}


ddm.hidden_select = function(opt_data, opt_ignored) {
  var output = '<select class="form-control hide" dir="' + soy.$$escapeHtmlAttribute(opt_data.dir) + '" id="' + soy.$$escapeHtmlAttribute(opt_data.name) + '" name="' + soy.$$escapeHtmlAttribute(opt_data.name) + '" ' + ((opt_data.multiple) ? 'multiple size="' + soy.$$escapeHtmlAttribute(opt_data.options.length) + '"' : '') + '>';
  var optionList117 = opt_data.options;
  var optionListLen117 = optionList117.length;
  for (var optionIndex117 = 0; optionIndex117 < optionListLen117; optionIndex117++) {
    var optionData117 = optionList117[optionIndex117];
    var selectedValue__soy102 = '' + ((opt_data.value && opt_data.value.value) ? soy.$$escapeHtml(opt_data.value.value) : '');
    output += '<option dir="' + soy.$$escapeHtmlAttribute(opt_data.dir) + '" ' + ((selectedValue__soy102 == optionData117.value) ? 'selected' : '') + ' value="' + soy.$$escapeHtmlAttribute(optionData117.value) + '">' + soy.$$escapeHtml(optionData117.label) + '</option>';
  }
  output += '</select>';
  return output;
};
if (goog.DEBUG) {
  ddm.hidden_select.soyTemplateName = 'ddm.hidden_select';
}


ddm.select_options = function(opt_data, opt_ignored) {
  var output = '';
  var optionList136 = opt_data.options;
  var optionListLen136 = optionList136.length;
  for (var optionIndex136 = 0; optionIndex136 < optionListLen136; optionIndex136++) {
    var optionData136 = optionList136[optionIndex136];
    var selectedValue__soy121 = '' + ((opt_data.value && opt_data.value.value) ? soy.$$escapeHtml(opt_data.value.value) : '');
    output += '<li class="' + ((selectedValue__soy121 == optionData136.value) ? 'option-selected' : '') + '" data-option-index="' + soy.$$escapeHtmlAttribute(optionIndex136) + '" data-option-value="' + soy.$$escapeHtmlAttribute(optionData136.value) + '">' + soy.$$escapeHtml(optionData136.label) + '</li>';
  }
  return output;
};
if (goog.DEBUG) {
  ddm.select_options.soyTemplateName = 'ddm.select_options';
}
