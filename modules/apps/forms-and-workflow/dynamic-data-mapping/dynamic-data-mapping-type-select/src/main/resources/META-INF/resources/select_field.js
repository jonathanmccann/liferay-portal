AUI.add(
	'liferay-ddm-form-field-select',
	function(A) {
		var Lang = A.Lang;

		var TPL_OPTION = '<option>{label}</option>';

		var SelectField = A.Component.create(
			{
				ATTRS: {
					dataSourceType: {
						getter: '_getDataSourceType',
						value: 'manual'
					},

					multiple: {
						state: true,
						value: false
					},

					options: {
						getter: '_getOptions',
						state: true,
						validator: Array.isArray,
						value: []
					},

					strings: {
						value: {
							chooseAnOption: Liferay.Language.get('choose-an-option'),
							dynamicallyLoadedData: Liferay.Language.get('dynamically-loaded-data')
						}
					},

					type: {
						value: 'select'
					},

					value: {
						value: []
					}
				},

				AUGMENTS: [
					Liferay.DDM.Field.SelectFieldSearchSupport
				],

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-select',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._eventHandlers.push(
							A.one('doc').after('click', A.bind(instance._afterClickOutside, instance)),
							instance.bindContainerEvent('mousedown', instance._afterClickSelectTrigger, '.form-builder-select-field'),
							instance.bindContainerEvent('mousedown', instance._onClickItem, 'li')
						);
					},

					cleanSelect: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						inputNode.setAttribute('selected', false);

						instance.set('value', []);
					},

					closeList: function() {
						var instance = this;

						var container = instance.get('container');

						if (!instance.get('readOnly') && instance._isListOpen()) {
							container.one('.drop-chosen').addClass('hide');

							container.one('.form-builder-select-field').removeClass('active');

							instance.fire('closeList');
						}
					},

					getTemplateContext: function() {
						var instance = this;

						return A.merge(
							SelectField.superclass.getTemplateContext.apply(instance, arguments),
							{
								options: instance.get('options'),
								selectCaretDoubleIcon: Liferay.Util.getLexiconIconTpl('caret-double-l', 'icon-monospaced'),
								selectSearchIcon: Liferay.Util.getLexiconIconTpl('search', 'icon-monospaced'),
								strings: instance.get('strings'),
								value: instance.getValueSelected()
							}
						);
					},

					getValue: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						var value;

						if (instance.get('multiple')) {
							value = [];

							inputNode.all('option').each(
								function(optionNode) {
									if (optionNode.attr('selected')) {
										value.push(optionNode.val());
									}
								}
							);

							value = value.join();
						}
						else {
							value = inputNode.val();
						}

						return value;
					},

					getValueSelected: function() {
						var instance = this;

						var value = instance.get('value');

						if (!Lang.isArray(value)) {
							value = [value];
						}

						var values = instance._getOptionsSelected(value);

						if (!instance.get('multiple')) {
							return values[0];
						}

						return values;
					},

					openList: function() {
						var instance = this;

						var container = instance.get('container');

						var selectGroup = container.one('.form-builder-select-field');

						container.one('.drop-chosen').toggleClass('hide');

						selectGroup.addClass('active');
					},

					render: function() {
						var instance = this;

						var dataSourceType = instance.get('dataSourceType');

						SelectField.superclass.render.apply(instance, arguments);

						if (dataSourceType !== 'manual' && instance.get('builder')) {
							var inputNode = instance.getInputNode();

							var strings = instance.get('strings');

							inputNode.attr('disabled', true);

							inputNode.html(
								Lang.sub(
									TPL_OPTION,
									{
										label: strings.dynamicallyLoadedData
									}
								)
							);
						}

						return instance;
					},

					showErrorMessage: function() {
						var instance = this;

						SelectField.superclass.showErrorMessage.apply(instance, arguments);

						var container = instance.get('container');

						var inputGroup = container.one('.input-select-wrapper');

						inputGroup.insert(container.one('.help-block'), 'after');
					},

					_afterClickOutside: function(event) {
						var instance = this;

						if (instance._isClickingOutSide(event)) {
							instance.closeList();
						}
					},

					_afterClickSelectTrigger: function(event) {
						event.preventDefault();

						var instance = this;

						var target = event.target;

						if (target.ancestor('.search-chosen')) {
							return;
						}

						if (!instance.get('readOnly')) {
							instance.openList();
						}
					},

					_getDataSourceType: function(value) {
						if (Lang.isString(value)) {
							try {
								value = JSON.parse(value);
							}
							catch (e) {
							}
						}

						if (Lang.isArray(value)) {
							value = value[0];
						}

						return value;
					},

					_getIndexOfOption: function(value, optionValue) {
						return value.indexOf(optionValue);
					},

					_getOptions: function(options) {
						return options || [];
					},

					_getOptionsSelected: function(value) {
						var instance = this;

						var options = instance.get('options');

						var optionsSelected = [];

						if (Lang.isArray(value)) {
							value.forEach(
								function(value, index) {
									options.forEach(
										function(option, index) {
											if (option.value.indexOf(value) > -1) {
												optionsSelected.push(option);
											}
										}
									);
								}
							);
						}

						return optionsSelected;
					},

					_isClickingOutSide: function(event) {
						var instance = this;

						var ancestor = event.target.ancestor('.form-builder-select-field');

						var container = instance.get('container');

						return !ancestor || ancestor !== container.one('.form-builder-select-field');
					},

					_isListOpen: function() {
						var instance = this;

						var container = instance.get('container');

						var openList = container.one('.drop-chosen').hasClass('hide');

						return !openList;
					},

					_onClickItem: function(event) {
						var instance = this;

						var value = event.target.getAttribute('data-option-value');

						instance.set('value', [value]);

						instance.render();
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').Select = SelectField;
	},
	'',
	{
		requires: ['liferay-ddm-form-field-select', 'liferay-ddm-form-field-select-search-support', 'liferay-ddm-form-renderer-field']
	}
);