import React, { useState } from 'react';
import { Header } from './components/Header';
import { TrendingBar } from './components/TrendingBar';
import { LeadColumn } from './components/LeadColumn';
import { CenterColumn } from './components/CenterColumn';
import { RightColumn } from './components/RightColumn';
import { Footer } from './components/Footer';
import { ArticleView } from './components/ArticleView';
import { SectionView } from './components/SectionView';
import { InteractiveWordle } from './components/InteractiveWordle';
import { InteractivePollModal } from './components/InteractivePollModal';
import { TodaysPaperModal } from './components/TodaysPaperModal';
import { SearchModal } from './components/SearchModal';
import { SubscribeModal } from './components/SubscribeModal';
import { LoginModal } from './components/LoginModal';
import { ARTICLES, TRENDING_TOPICS } from './data/newsData';
import { ActiveView } from './types/news';

export default function App() {
  const [activeView, setActiveView] = useState<ActiveView>({ type: 'home' });
  const [currentEdition, setCurrentEdition] = useState('U.S.');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userEmail, setUserEmail] = useState('');
  
  // Wireframe photos mode starts as true to match the exact screenshot uploaded by user
  const [useWireframePhotos, setUseWireframePhotos] = useState(true);

  // Modals state
  const [searchModalOpen, setSearchModalOpen] = useState(false);
  const [subscribeModalOpen, setSubscribeModalOpen] = useState(false);
  const [loginModalOpen, setLoginModalOpen] = useState(false);
  const [todaysPaperModalOpen, setTodaysPaperModalOpen] = useState(false);
  const [activeGameModal, setActiveGameModal] = useState<string | null>(null);
  const [activePollingModalState, setActivePollingModalState] = useState<string | null>(null);

  // Key articles matching layout
  const leadArticle = ARTICLES.find((a) => a.id === 'lead-women-leaving-trump') || ARTICLES[0];
  const secondArticle = ARTICLES.find((a) => a.id === 'press-secretary-law') || ARTICLES[2];
  const ukraineArticle = ARTICLES.find((a) => a.id === 'ukraine-satellite-limits') || ARTICLES[3];
  const electionsArticle = ARTICLES.find((a) => a.id === 'rust-belt-races') || ARTICLES[6];
  const aiArticle = ARTICLES.find((a) => a.id === 'ai-agents-dinner-party') || ARTICLES[7];
  const wirecutterArticle = ARTICLES.find((a) => a.id === 'wirecutter-vacuums') || ARTICLES[8];

  const handleSelectArticle = (articleId: string) => {
    setActiveView({ type: 'article', articleId });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleSelectSection = (section: string) => {
    if (section === 'Games') {
      setActiveGameModal('Wordle');
      return;
    }
    setActiveView({ type: 'section', sectionName: section });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleTrendingSelect = (topic: typeof TRENDING_TOPICS[number]) => {
    if (topic.action === 'game') {
      setActiveGameModal('Wordle');
    } else if (topic.articleId) {
      handleSelectArticle(topic.articleId);
    }
  };

  // Get current active article for reading view
  const currentArticle =
    activeView.type === 'article'
      ? ARTICLES.find((a) => a.id === activeView.articleId) || leadArticle
      : leadArticle;

  return (
    <div className="min-h-screen bg-white text-[#121212] flex flex-col font-sans-editorial">
      {/* Top Header & Navigation */}
      <Header
        currentEdition={currentEdition}
        onSelectEdition={setCurrentEdition}
        onOpenSearch={() => setSearchModalOpen(true)}
        onOpenSubscribe={() => setSubscribeModalOpen(true)}
        onOpenLogin={() => setLoginModalOpen(true)}
        onOpenTodaysPaper={() => setTodaysPaperModalOpen(true)}
        isLoggedIn={isLoggedIn}
        userEmail={userEmail}
        onGoHome={() => setActiveView({ type: 'home' })}
        activeSection={activeView.type === 'section' ? activeView.sectionName : undefined}
        onSelectSection={handleSelectSection}
        useWireframePhotos={useWireframePhotos}
        onTogglePhotoStyle={() => setUseWireframePhotos((prev) => !prev)}
      />

      {/* Main Screen Content */}
      <div className="flex-1">
        {activeView.type === 'home' && (
          <main className="w-full max-w-[1340px] mx-auto px-4 pb-12">
            {/* Trending Strip */}
            <TrendingBar onSelectTopic={handleTrendingSelect} />

            {/* 3-Column Editorial Grid: Left Lead (5 cols), Center Focus (4 cols), Right Sidebars (3 cols) */}
            <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
              <LeadColumn
                leadArticle={leadArticle}
                secondArticle={secondArticle}
                onSelectArticle={handleSelectArticle}
                useWireframePhotos={useWireframePhotos}
              />

              <CenterColumn
                ukraineArticle={ukraineArticle}
                electionsArticle={electionsArticle}
                aiArticle={aiArticle}
                onSelectArticle={handleSelectArticle}
                onOpenPollingModal={(st) => setActivePollingModalState(st)}
              />

              <RightColumn
                onSelectOpinion={(opinionId) => {
                  // Direct to article reader or opinion focus
                  handleSelectArticle('lead-women-leaving-trump');
                }}
                onOpenGame={(gameName) => setActiveGameModal(gameName)}
                onSelectArticle={handleSelectArticle}
                wirecutterArticle={wirecutterArticle}
              />
            </div>
          </main>
        )}

        {activeView.type === 'article' && (
          <ArticleView
            article={currentArticle}
            onBack={() => setActiveView({ type: 'home' })}
            onSelectArticle={handleSelectArticle}
            useWireframePhotos={useWireframePhotos}
          />
        )}

        {activeView.type === 'section' && (
          <SectionView
            sectionName={activeView.sectionName}
            onSelectArticle={handleSelectArticle}
            onBackToHome={() => setActiveView({ type: 'home' })}
            onOpenGame={(game) => setActiveGameModal(game)}
            useWireframePhotos={useWireframePhotos}
          />
        )}
      </div>

      {/* Global Footer */}
      <Footer
        onSelectSection={handleSelectSection}
        onOpenSubscribe={() => setSubscribeModalOpen(true)}
        onOpenLogin={() => setLoginModalOpen(true)}
      />

      {/* Interactive Modals */}
      {searchModalOpen && (
        <SearchModal
          onClose={() => setSearchModalOpen(false)}
          onSelectArticle={handleSelectArticle}
        />
      )}

      {subscribeModalOpen && (
        <SubscribeModal
          onClose={() => setSubscribeModalOpen(false)}
          onSubscribeSuccess={(email) => {
            setIsLoggedIn(true);
            setUserEmail(email);
          }}
        />
      )}

      {loginModalOpen && (
        <LoginModal
          onClose={() => setLoginModalOpen(false)}
          isLoggedIn={isLoggedIn}
          onLogout={() => {
            setIsLoggedIn(false);
            setUserEmail('');
          }}
          onLoginSuccess={(email) => {
            setIsLoggedIn(true);
            setUserEmail(email);
          }}
        />
      )}

      {todaysPaperModalOpen && (
        <TodaysPaperModal
          onClose={() => setTodaysPaperModalOpen(false)}
          onSelectArticle={handleSelectArticle}
        />
      )}

      {activeGameModal && (
        <InteractiveWordle
          initialGame={activeGameModal}
          onClose={() => setActiveGameModal(null)}
        />
      )}

      {activePollingModalState && (
        <InteractivePollModal
          initialState={activePollingModalState}
          onClose={() => setActivePollingModalState(null)}
        />
      )}
    </div>
  );
}
