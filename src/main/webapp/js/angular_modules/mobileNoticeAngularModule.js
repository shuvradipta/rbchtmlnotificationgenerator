/**
 * 
 */
(function(){
var app = angular.module('mobileNoticeApp',[]);

app.controller('mobileNoticeController',function(){
	this.xmlMessageCount = 0;
	
	this.getArray = function(){
		console.log("Message Count: " + this.xmlMessageCount);
		this.noticesList = new Array(this.xmlMessageCount);
		return this.noticesList;
	}
	

});
})();