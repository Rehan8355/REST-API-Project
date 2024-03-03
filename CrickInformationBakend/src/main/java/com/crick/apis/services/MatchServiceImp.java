package com.crick.apis.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import com.crick.apis.entities.Match;
import com.crick.apis.repositories.MatchRepo;

@Service
public class MatchServiceImp implements MatchService{

	private MatchRepo matchRepo;
	
	
	public MatchServiceImp(MatchRepo matchRepo) {
		
		this.matchRepo = matchRepo;
	}

	//find all match fromDB
	@Override
	public List<Match> getAllMatches() {
		
		return this.matchRepo.findAll();
	}

	  @Override
	    public List<Match> getLiveMatchs() {
	        List<Match> matches = new ArrayList<>();
	        try {
	            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
	            org.jsoup.nodes.Document document = Jsoup.connect(url).get();
	            org.jsoup.select.Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
	            for (org.jsoup.nodes.Element match : liveScoreElements) {
	                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
	                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
	                String matchNumberVenue = match.select("span").text();
	                org.jsoup.select.Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
	                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
	                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
	                org.jsoup.select.Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
	                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
	                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
	                String textLive = match.select("div.cb-text-live").text();
	                String textComplete = match.select("div.cb-text-complete").text();
	                //getting match link
	                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

	                Match match1 = new Match();
	                match1.setTeamHeading(teamsHeading);
	                match1.setMatchNumberVenue(matchNumberVenue);
	                match1.setBattingTeam(battingTeam);
	                match1.setBattingTeamScore(score);
	                match1.setBowlTeam(bowlTeam);
	                match1.setBowlTeamScore(bowlTeamScore);
	                match1.setLiveText(textLive);
	                match1.setMatchLink(matchLink);
	                match1.setTextComplete(textComplete);
	                match1.setMatchStatus();


	                matches.add(match1);

//	                update the match in database


	              updateMatch(match1);


	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return matches;
	    }
  	  private void updateMatch(Match match1) {

	        Match match = this.matchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
	        if (match == null) {
            this.matchRepo.save(match1);
	        } else {

	            match1.setMatchId(match.getMatchId());
	            this.matchRepo.save(match1);
	        }

	    }

  	@Override
	 public List<List<String>> getPointTable() {
		 List<List<String>> pointTable = new ArrayList<>();
	        String tableURL = "https://www.cricbuzz.com/cricket-series/6732/icc-cricket-world-cup-2023/points-table";
	        try {
	            org.jsoup.nodes.Document document = Jsoup.connect(tableURL).get();
	            org.jsoup.select.Elements table = document.select("table.cb-srs-pnts");
	            org.jsoup.select.Elements tableHeads = table.select("thead>tr>*");
	            List<String> headers = new ArrayList<>();
	            tableHeads.forEach(element -> {
	                headers.add(element.text());
	            });
	            pointTable.add(headers);
	            org.jsoup.select.Elements bodyTrs = table.select("tbody>*");
	            bodyTrs.forEach(tr -> {
	                List<String> points = new ArrayList<>();
	                if (tr.hasAttr("class")) {
	                    org.jsoup.select.Elements tds = tr.select("td");
	                    String team = tds.get(0).select("div.cb-col-84").text();
	                    points.add(team);
	                    tds.forEach(td -> {
	                        if (!td.hasClass("cb-srs-pnts-name")) {
	                            points.add(td.text());
	                        }
	                    });
//	                    System.out.println(points);
	                    pointTable.add(points);
	                }


	            });

	            System.out.println(pointTable);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return pointTable;
	    }
	}


	

