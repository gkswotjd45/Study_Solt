package example.controller;

import example.serivce.ButtonService;

	public class Button2Controller {

		public String getReuslt() {
			ButtonService service = new ButtonService();
			String reuslt = service.clickButton();
			// 내가 할수 있는 일 (로직) 메서드 이름 정의.
			return reuslt;
		}

	}

