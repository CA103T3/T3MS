const url = 'http://127.0.0.1:8000/test.html';

describe('role-attribute方式加載', function() {

	before(function() {
		cy.visit(url);
	});

	it('一般加載', function() {
		cy.get('.test-roleAttr-normal').within(function() {
			cy.get('> select:eq(0)')
				.should('have.attr.name', 'county')
				.should('have.class', 'county')
				.should('have.value', '')
				.find('option:selected').should('contain', '選擇縣市')

			cy.get('> select:eq(1)')
				.should('have.attr.name', 'district')
				.should('have.class', 'district')
				.should('have.value', '')
				.find('option:selected').should('contain', '---')

			cy.get('> input:eq(0)')
				.should('have.attr.name', 'zipcode')
				.should('have.class', 'zipcode')
				.should('not.contain')
				.should('not.have.value')
				.should('be.hidden')
		});
	});

	it('縣市選單進行選擇', function() {
		cy.get('.test-roleAttr-normal').within(function() {
			cy.get('.county').select('臺北市').should('have.value', '臺北市')
				.find('option:selected').should('contain', '臺北市')
			cy.get('.district').select('中正區').should('have.value', '中正區')
				.find('option:selected').should('contain', '中正區')
			cy.get('.zipcode').should('have.value', '100')

			cy.get('.county').select('澎湖縣').should('have.value', '澎湖縣')
				.find('option:selected').should('contain', '澎湖縣')
			cy.get('.district').select('馬公市').should('have.value', '馬公市')
				.find('option:selected').should('contain', '馬公市')
			cy.get('.zipcode').should('have.value', '880')
		});
	});

	it('選擇沒有值的縣市選項時，區域及郵遞區號應該重置', function() {
		cy.get('.test-roleAttr-normal').within(function() {
			cy.get('.county').select('選擇縣市').should('have.value', '')
			cy.get('.district').should('have.value', '').and('have.text', '---')
			cy.get('.zipcode').should('have.value', '')
		});
	});

	it('重新選擇縣市，區域及郵遞區號應該重置', function() {
		cy.get('.test-roleAttr-normal').within(function() {
			cy.get('.county').select('臺北市')
			cy.get('.district').select('中正區')
			cy.get('.county').select('新北市')
			cy.get('.district').should('have.value', '').contains('選擇區域')
			cy.get('.zipcode').should('have.value', '')
		});
	});

	it('顯示部分縣市-臺北市、臺中市、高雄市', function() {
		cy.get('.test-roleAttr-limit-counties').within(function() {
			cy.get('.county')
				.should('contain', '選擇縣市')
				.should('contain', '臺北市')
				.should('contain', '臺中市')
				.should('contain', '高雄市')
				.should('not.contain', '屏東縣')
				.should('have.attr.name', 'county')
				.should('have.class', 'county')
				.should('have.value', '')
				.find('option:selected').should('contain', '選擇縣市')

			cy.get('.district')
				.should('have.attr.name', 'district')
				.should('have.class', 'district')
				.should('have.value', '')
				.find('option:selected').should('contain', '---')

			cy.get('.zipcode')
				.should('have.attr.name', 'zipcode')
				.should('have.class', 'zipcode')
				.should('not.contain')
				.should('not.have.value')
				.should('be.hidden')

            cy.get('.county').select('臺北市')
            cy.get('.district option')
                .should('have.length', 13) // 應有12個區域選項加上空白選項
		});
	});

    it('顯示部分縣市及區域-臺北市(大同區、萬華區)、臺中市(西區)、高雄市(不設限)', function() {
		cy.get('.test-roleAttr-limit-counties-and-district').within(function() {
			cy.get('.county')
				.should('contain', '選擇縣市')
				.should('contain', '臺北市')
				.should('contain', '臺中市')
				.should('contain', '嘉義市')
				.should('not.contain', '屏東縣')
				.should('have.attr.name', 'county')
				.should('have.class', 'county')
				.should('have.value', '')
				.find('option:selected').should('contain', '選擇縣市')

			cy.get('.district')
				.should('have.attr.name', 'district')
				.should('have.class', 'district')
				.should('have.value', '')
				.find('option:selected').should('contain', '---')

		    cy.get('.county').select('臺北市')
            cy.get('.district')
                .should('contain', '大同區')
                .should('contain', '萬華區')

            cy.get('.county').select('臺中市')
            cy.get('.district')
                .should('not.contain', '大同區')
                .should('contain', '西區')

            cy.get('.county').select('嘉義市')
            cy.get('.district option')
                .should('have.length', 3) // 應有兩個區域選項加上空白選項
		});
	});

	it('預設選定新北市及其板橋區', function() {
		cy.get('.test-roleAttr-is-selected').within(function() {
			cy.get('.county').should('have.value', '新北市')
			cy.get('.district').should('have.value', '板橋區')
			cy.get('.zipcode').should('have.value', '220')
		});
	});

    it('預設選定新北市及其板橋區(錯別字容錯 data-selected-country)', function() {
		cy.get('.test-roleAttr-is-selected-2').within(function() {
			cy.get('.county').should('have.value', '新北市')
			cy.get('.district').should('have.value', '板橋區')
			cy.get('.zipcode').should('have.value', '220')
		});
	});

	it('顯示部分縣市-臺北市、臺中市、高雄市且預設選定臺中市及其西區', function() {
	  cy.get('.test-roleAttr-limit-and-selected').within(function() {
			cy.get('.county')
				.should('contain', '選擇縣市')
				.should('contain', '臺北市')
				.should('contain', '臺中市')
				.should('contain', '高雄市')
				.should('not.contain', '屏東縣')
				.should('have.value', '臺中市')

			cy.get('.district').should('have.value', '西區')
			cy.get('.zipcode').should('have.value', '403')
		});
	});

});

// -----------------------------------------------------------------------
// -----------------------------------------------------------------------
// -----------------------------------------------------------------------
// -----------------------------------------------------------------------

describe('實例方式加載', function() {

	before(function() {
		cy.visit(url);
	});

    it('一般加載', function() {
		cy.get('.test-object-normal').within(function() {
			cy.get('> select:eq(0)')
				.should('have.attr.name', 'county')
				.should('have.class', 'county')
				.should('have.value', '')
				.find('option:selected').should('contain', '選擇縣市')

			cy.get('> select:eq(1)')
				.should('have.attr.name', 'district')
				.should('have.class', 'district')
				.should('have.value', '')
				.find('option:selected').should('contain', '---')

			cy.get('> input:eq(0)')
				.should('have.attr.name', 'zipcode')
				.should('have.class', 'zipcode')
				.should('not.contain')
				.should('not.have.value')
				.should('be.hidden')
		});
	});

	it('縣市選單進行選擇', function() {
		cy.get('.test-object-normal').within(function() {
			cy.get('.county').select('臺北市').should('have.value', '臺北市')
				.find('option:selected').should('contain', '臺北市')
			cy.get('.district').select('中正區').should('have.value', '中正區')
				.find('option:selected').should('contain', '中正區')
			cy.get('.zipcode').should('have.value', '100')

			cy.get('.county').select('澎湖縣').should('have.value', '澎湖縣')
				.find('option:selected').should('contain', '澎湖縣')
			cy.get('.district').select('馬公市').should('have.value', '馬公市')
				.find('option:selected').should('contain', '馬公市')
			cy.get('.zipcode').should('have.value', '880')
		});
	});

	it('選擇沒有值的縣市選項時，區域及郵遞區號應該重置', function() {
		cy.get('.test-object-normal').within(function() {
			cy.get('.county').select('選擇縣市').should('have.value', '')
			cy.get('.district').should('have.value', '').and('have.text', '---')
			cy.get('.zipcode').should('have.value', '')
		});
	});

	it('重新選擇縣市，區域及郵遞區號應該重置', function() {
		cy.get('.test-object-normal').within(function() {
			cy.get('.county').select('臺北市')
			cy.get('.district').select('中正區')
			cy.get('.county').select('新北市')
			cy.get('.district').should('have.value', '').contains('選擇區域')
			cy.get('.zipcode').should('have.value', '')
		});
	});

    it('顯示部分縣市-臺北市、臺中市、高雄市', function() {
		cy.get('.test-object-limit-counties').within(function() {
			cy.get('.county')
				.should('contain', '選擇縣市')
				.should('contain', '臺北市')
				.should('contain', '臺中市')
				.should('contain', '高雄市')
				.should('not.contain', '屏東縣')
				.should('have.attr.name', 'county')
				.should('have.class', 'county')
				.should('have.value', '')
				.find('option:selected').should('contain', '選擇縣市')

			cy.get('.district')
				.should('have.attr.name', 'district')
				.should('have.class', 'district')
				.should('have.value', '')
				.find('option:selected').should('contain', '---')

			cy.get('.zipcode')
				.should('have.attr.name', 'zipcode')
				.should('have.class', 'zipcode')
				.should('not.contain')
				.should('not.have.value')
				.should('be.hidden')

            cy.get('.county').select('臺北市')
            cy.get('.district option')
                .should('have.length', 13) // 應有12個區域選項加上空白選項
		});
	});

    it('顯示部分縣市及區域-臺北市(大同區、萬華區)、臺中市(西區)、高雄市(不設限)', function() {
		cy.get('.test-object-limit-counties-and-district').within(function() {
			cy.get('.county')
				.should('contain', '選擇縣市')
				.should('contain', '臺北市')
				.should('contain', '臺中市')
				.should('contain', '嘉義市')
				.should('not.contain', '屏東縣')
				.should('have.attr.name', 'county')
				.should('have.class', 'county')
				.should('have.value', '')
				.find('option:selected').should('contain', '選擇縣市')

			cy.get('.district')
				.should('have.attr.name', 'district')
				.should('have.class', 'district')
				.should('have.value', '')
				.find('option:selected').should('contain', '---')

		    cy.get('.county').select('臺北市')
            cy.get('.district')
                .should('contain', '大同區')
                .should('contain', '萬華區')

            cy.get('.county').select('臺中市')
            cy.get('.district')
                .should('not.contain', '大同區')
                .should('contain', '西區')

            cy.get('.county').select('嘉義市')
            cy.get('.district option')
                .should('have.length', 3) // 應有兩個區域選項加上空白選項
		});
	});

});
