@CheckWeather
Feature: To test a website

  Scenario Outline: To go to BBC weather page and see southampton forecast
    Given customer open "<Browser>"
    Given customer is on bbc weather page "BbcWeather"
    And customer types "so146hz" in the "CitySearchBox"
    And customer clicks "Keys.ENTER" in "CitySearchBox"
    Then customer should see forcast displayed showing "Area" on the page
    And customer closes browser

    Examples: 
      | Browser |
      | FIREFOX |
      | CHROME  |
